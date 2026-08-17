---
name: apac-sdlc-dockerize
description: "Containerize a project. Detects runtime + languages + project layout, generates production-ready Dockerfile(s), composes multi-service stacks via docker-compose, reuses existing host services (databases, caches) to avoid duplicate resources, and verifies the stack runs. Triggers on: 'dockerize this', 'containerize', 'add docker', 'create Dockerfile', 'make it run in docker', 'compose this up'."
trigger: /apac-sdlc-dockerize
---

# /apac-sdlc-dockerize

Take a project from "runs on my machine" to "runs in containers" with production-quality defaults. Six phases — each is a gate. Stop and ask before generating anything.

## Phase 1 — Detect container runtime

Probe in order; stop at the first that works:

```bash
docker --version
podman --version
nerdctl --version
colima status   # macOS users sometimes have docker CLI without a running engine
```

Then verify the engine actually responds:

```bash
docker info >/dev/null 2>&1 || echo "engine not running"
```

| Finding | Action |
|---|---|
| CLI present + engine running | Continue. |
| CLI present, engine down | Ask user: start engine (`open -a Docker`, `colima start`, `systemctl start docker`)? |
| Nothing installed | **Stop.** Ask user to install Docker Desktop / Colima / Podman. Print exact one-line install per OS (`brew install --cask docker`, `brew install colima docker`, `apt install docker.io`, etc). Do **not** continue until user confirms install. |

Do not assume Docker. Some users run Podman or rootless Docker — generated files must work on both. Avoid Docker-Desktop-only features (e.g. `host.docker.internal` without fallback).

## Phase 2 — Analyze project

### Detect languages / package managers

| Signal | Stack | Manager |
|---|---|---|
| `package.json` + `package-lock.json` | Node | npm |
| `package.json` + `yarn.lock` | Node | yarn |
| `package.json` + `pnpm-lock.yaml` | Node | pnpm |
| `pyproject.toml` + `poetry.lock` | Python | poetry |
| `pyproject.toml` + `uv.lock` | Python | uv |
| `requirements.txt` | Python | pip |
| `go.mod` | Go | go |
| `Cargo.toml` | Rust | cargo |
| `pom.xml` | Java | maven |
| `build.gradle*` | Java/Kotlin | gradle |
| `Gemfile` | Ruby | bundler |
| `composer.json` | PHP | composer |
| `*.csproj` / `*.sln` | .NET | dotnet |

Read the manifest to extract: app entry point, declared port, scripts (`start`, `dev`, `build`), runtime version (e.g. `engines.node`, `python_requires`).

### Detect single vs multi-project

Multi-project signals — if any present, treat as monorepo:

- `pnpm-workspace.yaml`, `lerna.json`, `nx.json`, `turbo.json`, `rush.json`
- `go.work`
- Multiple manifests in `apps/`, `services/`, `packages/`, `cmd/`
- Top-level `docker-compose.yml` already referencing multiple build contexts

For each sub-project, repeat the language/manager detection. Treat each as a separate Dockerfile target.

### Detect declared infra dependencies

Grep app config for service references that imply backing services:

- Postgres: `DATABASE_URL=postgres://…`, `pg`, `psycopg`, `sqlalchemy postgres`, `prisma` provider `postgresql`
- MySQL/MariaDB: `mysql://…`, `mysql2`, `pymysql`
- Redis: `REDIS_URL`, `redis-py`, `ioredis`
- Mongo: `MONGO_URL`, `mongoose`, `pymongo`
- RabbitMQ / Kafka / Elasticsearch / Minio — same pattern

These become candidate services in Phase 4.

## Phase 3 — Generate Dockerfile

Produce a **multi-stage** Dockerfile per project. Mandatory baseline rules:

1. **Pinned base image** — `node:20.11-alpine`, not `node:latest`.
2. **Multi-stage** — `builder` stage with full toolchain, `runtime` stage with only artifacts + runtime deps.
3. **Layer ordering for cache** — copy lockfile/manifest first, install deps, *then* copy source. Code changes must not bust dependency cache.
4. **Non-root user** — `USER node` (Node image has it), or `RUN adduser -D app && USER app`.
5. **`.dockerignore`** — always emit alongside. Exclude `node_modules`, `.git`, `dist`, `build`, `.env*`, IDE folders, tests if not needed at runtime.
6. **`HEALTHCHECK`** — when an HTTP port exists, add a curl/wget probe against `/health` or the declared port.
7. **`EXPOSE`** — declared port from config, not hardcoded.
8. **Minimal runtime layer** — no apt cache (`rm -rf /var/lib/apt/lists/*`), `--no-install-recommends`, no build toolchain in runtime stage.
9. **Reproducibility** — `npm ci` not `npm install`, `pip install --no-cache-dir`, `go build -trimpath -ldflags="-s -w"`.

### Per-language skeletons

Reach for these as starting points. Adapt to the project, don't paste blind.

**Node (npm):**
```dockerfile
ARG NODE_VERSION=20.11-alpine
FROM node:${NODE_VERSION} AS builder
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build

FROM node:${NODE_VERSION} AS runtime
WORKDIR /app
ENV NODE_ENV=production
COPY package*.json ./
RUN npm ci --omit=dev && npm cache clean --force
COPY --from=builder /app/dist ./dist
USER node
EXPOSE 3000
HEALTHCHECK --interval=30s --timeout=3s CMD wget -qO- http://localhost:3000/health || exit 1
CMD ["node", "dist/index.js"]
```

**Python (uv):**
```dockerfile
FROM python:3.12-slim AS builder
WORKDIR /app
RUN pip install --no-cache-dir uv
COPY pyproject.toml uv.lock ./
RUN uv sync --frozen --no-dev

FROM python:3.12-slim AS runtime
WORKDIR /app
COPY --from=builder /app/.venv /app/.venv
COPY . .
ENV PATH="/app/.venv/bin:$PATH"
RUN adduser --disabled-password --gecos "" app && chown -R app /app
USER app
EXPOSE 8000
CMD ["uvicorn", "app.main:app", "--host", "0.0.0.0", "--port", "8000"]
```

**Go:**
```dockerfile
FROM golang:1.22-alpine AS builder
WORKDIR /src
COPY go.mod go.sum ./
RUN go mod download
COPY . .
RUN CGO_ENABLED=0 go build -trimpath -ldflags="-s -w" -o /out/app ./cmd/server

FROM gcr.io/distroless/static-debian12 AS runtime
COPY --from=builder /out/app /app
USER nonroot:nonroot
EXPOSE 8080
ENTRYPOINT ["/app"]
```

Always show the user the generated Dockerfile **before writing it**, summarize the choices (base image, port, user, healthcheck), invite changes.

## Phase 4 — Compose (multi-project only)

Only when Phase 2 found >1 sub-project OR backing services. Single-app, no-DB projects: skip compose, ship the Dockerfile.

Emit `docker-compose.yml` with:

- `services:` for each app (`build:` pointing at its Dockerfile) + each backing service.
- Pinned image tags for services (`postgres:16-alpine`, `redis:7-alpine`).
- `healthcheck:` on each backing service.
- `depends_on:` with `condition: service_healthy` so apps wait for DB readiness.
- Named volumes for stateful services (`pgdata`, `redisdata`).
- Single user-defined network so services resolve each other by name.
- `env_file: .env` per app. Generate a `.env.example` with the variables used.
- No host port collisions — pick free ports, surface them to the user.

## Phase 5 — Reuse existing services

Before adding a `postgres` / `redis` / etc. service to compose, check the host:

```bash
docker ps --format '{{.Names}}\t{{.Image}}\t{{.Ports}}\t{{.Status}}'
```

For each candidate backing service detected in Phase 2, match by image prefix (`postgres`, `redis`, `mongo`, etc.). Present findings:

```
Found running services on host:
  pg-dev          postgres:15-alpine    0.0.0.0:5432->5432/tcp    Up 4 days
  redis-shared    redis:7-alpine        0.0.0.0:6379->6379/tcp    Up 2 hours

Reuse or create new?
  [1] reuse pg-dev — point app at host.docker.internal:5432 (or shared network)
  [2] new container — adds `db` service to compose, isolated volume
```

Default to **reuse** for stateful infra (DB, Redis, queues) — duplicating eats disk and ports. Default to **new** for app containers.

If reusing: do **not** add the service to compose. Instead, set the app's env var to point at it (`DATABASE_URL=postgres://user:pass@host.docker.internal:5432/db` on Docker Desktop, or attach to the existing service's network on Linux). Note the dependency in compose comments so future readers understand why.

## Phase 6 — Verify

After files are written, run the stack and confirm health:

```bash
docker compose up -d --build
docker compose ps
docker compose logs --tail=50
```

Check:

- All services are `Up` and healthchecks pass (`(healthy)` in `ps`).
- App responds on its declared port (`curl -fsS localhost:<port>/<health-path>`).
- Logs show no boot errors.

If anything fails:

1. Read the failing service's logs.
2. Diagnose: missing env var, port collision, build error, healthcheck path wrong, depends_on misordered.
3. Propose the fix. Re-run. Do **not** silently retry.

When green: capture the run command in the project's README ("How to run locally") and tell the user how to tear down (`docker compose down -v` to wipe volumes).

## Anti-patterns

- **Don't `FROM ubuntu:latest`** then `apt install` everything — pick a slim/alpine/distroless base for the language.
- **Don't run as root.** Almost every base image supports a non-root user; use it.
- **Don't copy `node_modules` from host.** Always install inside the container; native deps break otherwise.
- **Don't bake secrets into the image.** Use `ARG` only for non-secrets and `env_file`/runtime env for secrets.
- **Don't add a database to compose if one already runs on the host.** Reuse > duplicate.
- **Don't skip the healthcheck.** `depends_on` without `service_healthy` causes apps to crash-loop against not-yet-ready DBs.
- **Don't generate before showing.** Always preview the Dockerfile/compose to the user first.

## When *not* to use

- Project is a static site → use a CDN/host, not Docker.
- Project is already containerized and runs cleanly → use `/apac-sdlc-code-review` on the Dockerfile instead.
- User needs Kubernetes manifests, not local containers → different skill (out of scope).
