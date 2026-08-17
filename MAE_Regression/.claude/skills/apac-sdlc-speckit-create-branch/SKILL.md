---
name: "apac-sdlc-speckit-create-branch"
description: "Create and switch to a git branch for the feature before the spec is drafted. Invoked automatically as a before_specify hook (runs after the kb-context hook)."
compatibility: "Requires spec-kit project structure with .specify/ directory"
metadata:
  author: "apac-sdlc-framework"
  extension: "feature-branch"
user-invocable: true
disable-model-invocation: false
---

# Create Feature Branch (feature-branch extension)

This skill is the `feature-branch` extension. It is invoked as a **mandatory `before_specify`
hook** (command `speckit.create-branch`, registered in `.specify/extensions.yml`), ordered
**after** the `kb-context` hook (KB context is loaded first, then the feature branch is created).

`/apac-sdlc-speckit-specify`'s Step 2 already expects a `before_specify` hook to have created the
branch and to surface `BRANCH_NAME`/`FEATURE_NUM` — this skill fulfils that contract. `/specify`
still creates the spec directory itself (the branch name does not dictate the spec directory name).

It does **not** modify `create-new-feature.sh`; it uses that script's `--dry-run` mode only to
**compute** the branch name (side-effect-free — no branch, directory, or file is created), then runs
`git` directly to create the branch.

**This skill is a hook-free leaf:** it MUST NOT read `.specify/extensions.yml` or dispatch any
further hooks. It creates the branch, logs, and returns.

## Execution

### 1. Determine the feature description

Use the text the user typed after `/apac-sdlc-speckit-specify` in the triggering message (also in
`$ARGUMENTS`). If the user explicitly provided `GIT_BRANCH_NAME`, pass it as `--short-name <value>`.

### 2. Compute the branch name (no side effects)

Read `branch_numbering` from `.specify/init-options.json`. If it is `timestamp`, add `--timestamp`;
if `sequential` (or absent), omit it. Then run, from the project root:

```bash
.specify/scripts/bash/create-new-feature.sh --json --dry-run [--timestamp] [--short-name <slug>] "<feature description>"
```

`--dry-run` computes and prints the branch name **without creating anything**. Parse `BRANCH_NAME`
(e.g. `20260319-143022-user-auth`) from the JSON.

### 3. Create and switch to the branch (git only)

If the project root is **not** a git repository, skip — log and report it, then return:

```bash
.specify/scripts/bash/extension-log.sh feature-branch before_specify skipped "no-git"
```

Otherwise:

```bash
.specify/scripts/bash/extension-log.sh feature-branch before_specify start
git checkout -b "<BRANCH_NAME>"      # if it already exists: git checkout "<BRANCH_NAME>"
.specify/scripts/bash/extension-log.sh feature-branch before_specify done "branch=<BRANCH_NAME>"
```

### 4. Report

Tell the user the branch that was created/switched to, e.g.:

```
[feature-branch] created and switched to branch: 20260319-143022-user-auth
```

Then return control. `/apac-sdlc-speckit-specify` then drafts the spec on this branch and creates
the spec directory + `feature.json` itself.

## Graceful degradation

- Not a git repo → skip, log `skipped "no-git"`, continue.
- Branch already exists → switch to it instead of failing.
- `create-new-feature.sh` or the logger missing/fails → continue (no branch is fine; `/specify`
  still works on the current branch).
