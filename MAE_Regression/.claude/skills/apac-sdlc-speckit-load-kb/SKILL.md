---
name: "apac-sdlc-speckit-load-kb"
description: "Load relevant knowledge base context (rag / llm-wiki / graphify) for the current feature so the spec or clarify command that follows can use it. Invoked automatically as a before_specify / before_clarify hook."
argument-hint: "Optional: feature description or focus area to scope the KB query"
compatibility: "Requires spec-kit project structure with .specify/ directory"
metadata:
  author: "apac-sdlc-framework"
  extension: "kb-context"
user-invocable: true
disable-model-invocation: false
---

# Load Knowledge Base Context (kb-context extension)

This skill is the `kb-context` extension. It is invoked as a **mandatory `before_specify` and
`before_clarify` hook** (command `speckit.load-kb`, registered in `.specify/extensions.yml`). It
loads *only the relevant* knowledge base context for the current feature into the session. Because
the hook runs in the same session immediately before `/apac-sdlc-speckit-specify` or
`/apac-sdlc-speckit-clarify`, the context loaded here is available to that command directly — there
is no hand-off file to read.

**This skill is a hook-free leaf:** it MUST NOT read `.specify/extensions.yml` or dispatch any
further hooks. It loads context, logs, and returns.

## Execution

### 1. Determine the event and feature context

Infer which command triggered this hook from the conversation:

- Before **`/apac-sdlc-speckit-specify`**: `EVENT = before_specify`. The feature context is the
  feature description the user typed in the triggering `/specify` message (also in `$ARGUMENTS`).
- Before **`/apac-sdlc-speckit-clarify`**: `EVENT = before_clarify`. Run
  `.specify/scripts/bash/check-prerequisites.sh --json --paths-only` once, parse `FEATURE_SPEC`,
  read the current `spec.md`, and use the feature name plus ambiguous/partial sections as context.
- If you cannot tell, default `EVENT = before_specify` and use whatever feature description is
  available; if none, set `EVENT = unknown`.

### 2. Log the start

```bash
.specify/scripts/bash/extension-log.sh kb-context <EVENT> start
```

(If the script is missing or fails, continue — logging is best-effort and must never block.)

### 3. Check for an integrated KB

Check whether `.specify/kb/` exists and contains any `.md` files.

- If not, this project has no KB integrated. Log a skip and stop:

  ```bash
  .specify/scripts/bash/extension-log.sh kb-context <EVENT> skipped "no-kb"
  ```

  Return control silently.

### 4. Load and apply relevant context

For each `.md` file in `.specify/kb/` (run once — not per draft iteration):

1. Read the file — it describes a KB type's **detection signal** and a targeted **query approach**.
2. Check whether the KB's detection signal is present at the project root. If absent, skip this KB
   silently and log `skipped "signal-absent"`.
3. Follow the query instructions to load context relevant to the feature context from Step 1:
   - **rag** → call the `rag_query` MCP tool with the feature context as the query and
     `mode="hybrid"` (or the `rag_mode` from `.specify/extensions/kb-context/kb-config.yml`).
   - **llm-wiki** → read `llm-wiki/index.md`, pick the 3–5 most relevant entries, read those pages.
   - **graphify** → read `graphify-out/GRAPH_REPORT.md` and/or run `graphify query "<key concepts>"`.
4. If any shell command in the query instructions fails or is not on PATH, skip it silently.

Do **not** load the entire knowledge base — only what is relevant to the feature context.

### 5. Surface the context and log completion

Summarize the retrieved context in your reply (the key entities, decisions, constraints, and source
references) so the `/specify` or `/clarify` command that follows uses it directly. Then log:

```bash
.specify/scripts/bash/extension-log.sh kb-context <EVENT> done "<pattern>: <N items|excerpts>"
```

## Output

Briefly tell the user what was loaded, e.g.:

```
[kb-context] Loaded <pattern> context for "<feature>" (<N> items) — available to the next command.
```

or, when there is no KB:

```
[kb-context] No knowledge base integrated (.specify/kb/ empty) — skipped.
```

Then return control so the calling `/specify` or `/clarify` command proceeds with this context in
session.

## Graceful degradation

- No `.specify/kb/` → skip, log `skipped "no-kb"`.
- Detection signal absent → skip that KB, log `skipped "signal-absent"`.
- A query command errors or is not installed → skip that KB silently.
- The logger script missing/failing → continue anyway.
