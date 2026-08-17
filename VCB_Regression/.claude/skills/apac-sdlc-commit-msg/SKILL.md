---
name: apac-sdlc-commit-msg
description: "Generate a Conventional Commits message from staged git changes. Produces a concise subject line plus an optional body explaining the why, ready to pass to git commit."
trigger: /apac-sdlc-commit-msg
---

# /commit-msg

Generate a Conventional Commits message from the current staged diff. Subject under 50 chars, imperative mood, body only when the *why* is non-obvious.

## Usage

```
/commit-msg                    # message from currently staged changes
/commit-msg --all              # include unstaged tracked changes
/commit-msg --scope <scope>    # force a scope, e.g. api, ui, deps
/commit-msg --type <type>      # force a type, e.g. feat, fix, chore
/commit-msg --copy             # copy output to clipboard
/commit-msg --commit           # run git commit -m with the result
```

## Output Format

```
<type>(<scope>): <subject>

<body>

<footer>
```

- `type`: feat | fix | refactor | docs | test | chore | perf | build | ci | revert
- `scope`: optional, lowercase, single token (module/package/area)
- `subject`: imperative, no trailing period, ≤ 50 chars
- `body`: wrap at 72 chars, explain *why* not *what*, omit if obvious
- `footer`: `BREAKING CHANGE: …`, `Closes #123`, etc.

## Rules

- One logical change per commit. If diff spans unrelated changes, propose splitting.
- Breaking changes → `!` after type/scope **and** `BREAKING CHANGE:` footer.
- No filler: skip "this commit", "update files", "various changes".
- Detect type from diff: new files/exports → `feat`, bug patterns → `fix`, test-only → `test`, deps bump → `chore(deps)`, rename/move only → `refactor`.
- Scope inferred from top-level changed dir if not provided.

## Examples

```
feat(auth): add refresh-token rotation

Prevent replay after token theft by issuing a new refresh token
on every use and revoking the prior one.

Closes #482
```

```
fix(api): handle empty payload on POST /jobs

Returned 500 instead of 400 when body was missing.
```

```
chore(deps): bump zod to 3.23.0
```
