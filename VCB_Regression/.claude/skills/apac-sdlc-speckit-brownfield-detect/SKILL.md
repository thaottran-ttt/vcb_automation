---
name: "apac-sdlc-speckit-brownfield-detect"
description: "Before the constitution is written: detect brownfield vs greenfield, then either auto-draft the constitution sections from the existing codebase (brownfield) or interview the user section by section (greenfield). Invoked automatically as a before_constitution hook."
compatibility: "Requires spec-kit project structure with .specify/ directory"
metadata:
  author: "apac-sdlc-framework"
  extension: "brownfield-detect"
user-invocable: true
disable-model-invocation: false
---

# Brownfield Detect & Bootstrap (brownfield-detect extension)

This skill is the `brownfield-detect` extension. It is invoked as a **mandatory `before_constitution`
hook** (command `speckit.brownfield-detect`, registered in `.specify/extensions.yml`). It owns the
brownfield/greenfield decision **and** the section-gathering that precedes constitution drafting:

- **brownfield** (existing code) → scan the codebase and **auto-draft** Sections I–VIII, then have
  the user review/approve.
- **greenfield** (no code yet) → **interview** the user section by section for Sections I–VIII.

The Sections I–VIII content gathered here stays in the session. When this hook returns,
`/apac-sdlc-speckit-constitution` composes and writes `.specify/memory/constitution.md` from that
gathered content (its Step 2 already uses values supplied in the conversation).

**This skill is a hook-free leaf:** it MUST NOT read `.specify/extensions.yml` or dispatch any
further hooks. It detects, gathers, logs, and returns — it does not write the constitution file.

## Execution

### 1. Detect the mode

Run the bundled detector from the project root and read its `MODE=` line:

```bash
.specify/extensions/brownfield-detect/scripts/bash/detect.sh
```

It prints `MODE=brownfield` or `MODE=greenfield` plus the evidence, and appends a `brownfield-detect`
entry to `.specify/logs/extensions.log`. If the script is missing or fails, fall back to checking
inline whether the project has existing code (any of: `package.json`, `pyproject.toml`, `go.mod`,
`Cargo.toml`, `pom.xml`, `requirements.txt`, `Makefile`, a non-empty `src/`/`app/` directory, or a
substantive `README.md`).

Announce the result, e.g. `[brownfield-detect] brownfield (evidence: package.json, src/)`.

### 2a. Brownfield → auto-draft Sections I–VIII

Scan the following files to infer answers for each section — read only what exists, skip silently
if absent:

| Section | Files to scan |
|---------|--------------|
| I — Project Overview | `README.md`, `package.json` (`description`, `name`), `pyproject.toml` |
| II — Technology Stack | `package.json` (`dependencies`, `devDependencies`), `requirements.txt`, `go.mod`, `Cargo.toml`, `Dockerfile`, `docker-compose.yml` |
| III — Architecture Principles | Top-level directory structure, existing `CLAUDE.md`, `docs/` folder, `docker-compose.yml` |
| IV — Code Style & Conventions | `.eslintrc*`, `.prettierrc*`, `tsconfig.json`, `pyproject.toml` (ruff/black config), `biome.json` — and 2–3 existing source files for naming patterns |
| V — Testing Standards | `jest.config.*`, `vitest.config.*`, `pytest.ini`, `.github/workflows/`, `package.json` (`scripts.test`), presence of `__tests__/` or `*.test.*` files |
| VI — Security & Compliance | Auth-related packages in dependencies, `.env.example`, `SECURITY.md` |
| VII — Performance Targets | Existing benchmarks, monitoring config, `k6/` or `lighthouse` setup |
| VIII — Definition of Done | `.github/pull_request_template.md`, `CONTRIBUTING.md`, CI workflow files |

After scanning, compose a **draft of Sections I–VIII** based solely on what the codebase evidence
shows. For sections with no evidence, write a sensible default clearly marked
`(inferred default — please confirm)`. Present it for review:

```
I've scanned your codebase and pre-filled the constitution sections. Review each one
and tell me what to change — or say 'approve' to use them as-is.

[DRAFT — Sections I–VIII]
```

Incorporate the user's edits. The approved Sections I–VIII content now stands in the session for
`/apac-sdlc-speckit-constitution` to compose from. **Skip the interview below.**

### 2b. Greenfield → interview Sections I–VIII

Work through each section in order. Present a brief purpose, ask one targeted question, and wait for
the answer before moving on. Do not present all sections at once.

- **Section I — Project Overview** — "What is this project? Describe it in 2–4 sentences: what it
  does, who it's for, and what problem it solves." If vague, ask one follow-up about primary users
  and the outcome delivered.
- **Section II — Technology Stack** — "What languages, frameworks, databases, and infrastructure
  does this project use? (e.g., TypeScript + Next.js + PostgreSQL + Vercel)". If "TBD", record a
  placeholder and note it must be filled before `/apac-sdlc-speckit-plan`.
- **Section III — Architecture Principles** — "Any key architectural decisions or constraints?
  (e.g., 'monorepo', 'no microservices', 'all API calls go through a BFF', 'mobile-first')". If
  none, record "(No architecture constraints defined.)".
- **Section IV — Code Style & Conventions** — "What are your code style and naming conventions?
  (e.g., 'ESLint + Prettier', 'snake_case DB columns, camelCase JS', 'named exports only')". If
  none, record "(Use language defaults and existing codebase conventions.)".
- **Section V — Testing Standards** — "What are your testing requirements? (e.g., 'Jest + RTL, 80%
  coverage, Playwright E2E for critical paths')". If none, record "(No formal testing standards —
  follow constitution Section IX Goal-Driven Execution.)".
- **Section VI — Security & Compliance** — "Any security or compliance requirements? (e.g., 'JWT
  auth, OWASP Top 10, no PII in logs, SOC 2 in scope')". If none, record "(No specific security or
  compliance requirements beyond standard secure coding practices.)".
- **Section VII — Performance Targets** — "Any specific performance targets? (e.g., 'p95 API
  latency < 200ms', 'Lighthouse > 90', '10,000 concurrent users')". If none, record "(No specific
  performance targets — optimize when profiling shows a need.)".
- **Section VIII — Definition of Done** — "What does 'done' mean for a feature here? (e.g., 'code
  reviewed, tests passing, deployed to staging, documented')". If none, default to "Code reviewed,
  all tests passing, no regressions, merged to main branch.".

The collected Sections I–VIII answers now stand in the session for
`/apac-sdlc-speckit-constitution` to compose from.

## Return

Return control so `/apac-sdlc-speckit-constitution` proceeds. It composes and writes the
constitution from the Sections I–VIII content gathered above (plus the locked Section IX).

## Graceful degradation

- Detector script missing or errors → detect inline (markers above), then continue.
- Logger script missing/failing → continue anyway.
