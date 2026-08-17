---
name: apac-sdlc-optimize-claude-md
description: "Merge the apac-sdlc base CLAUDE.md template into the project's existing CLAUDE.md and trim under 200 lines by relocating verbose content into .claude/docs/ and rule lists into .claude/rules/."
trigger: /apac-sdlc-optimize-claude-md
---

# /apac-sdlc-optimize-claude-md

Optimize the project's root `CLAUDE.md` so it stays focused (≤ 200 lines) and
acts as an index. Verbose content moves to `.claude/docs/<topic>.md`;
rule/convention lists move to `.claude/rules/<topic>.md`. The root file is
rewritten to summarize and `@`-import each topic.

## Usage

```
/apac-sdlc-optimize-claude-md
```

No arguments. Operates on the project root.

## Inputs

- `CLAUDE.md` at the project root (required).
- The apac-sdlc base template — locate by running:
  ```bash
  node -e "console.log(require('path').resolve(require.resolve('apac-sdlc/package.json'), '../src/templates/init/CLAUDE.md'))"
  ```
  If that fails (apac-sdlc not installed as a package), the base template
  body is:
  ```md
  # CLAUDE.md
  ## Project overview
  ## Working with this project
  ## Build & Test
  ## Rules    → @.claude/rules/
  ## Docs     → @.claude/docs/
  ## Conventions
  ```
  Use those headings as the skeleton.

## What to do

1. **Read** the existing `CLAUDE.md` and the base template above.
2. **Classify** every section of the existing file:
   - **Keep in root** (concise): project description, build/test commands,
     high-level architecture summary, top-level conventions summary.
   - **Move to `.claude/docs/<topic>.md`**: verbose architecture deep-dives,
     internals, path-resolution explanations, extension/how-to guides,
     historical notes.
   - **Move to `.claude/rules/<topic>.md`**: multi-item rule lists, style
     guides, do/don't lists, coding conventions, naming rules.
3. **Pick kebab-case filenames** that describe each topic
   (e.g. `architecture.md`, `path-resolution.md`, `commit-style.md`).
4. **Create** the target files under `.claude/docs/` / `.claude/rules/`.
   Preserve the original content verbatim — do not paraphrase or delete.
5. **Rewrite root `CLAUDE.md`** to match the base template skeleton:
   - `# CLAUDE.md` heading and one-line intro.
   - `## Project overview` — one paragraph.
   - `## Working with this project` — note that apac-sdlc skills live in
     `.claude/skills/`.
   - `## Build & Test` — kept verbatim if short; otherwise summary + link.
   - `## Rules` followed by `@.claude/rules/` (or per-file `@`-imports if
     more discoverable).
   - `## Docs` followed by `@.claude/docs/` (or per-file `@`-imports).
   - `## Conventions` — short bullet summary; full lists live in
     `.claude/rules/<topic>.md`.
6. **Verify** `wc -l CLAUDE.md` ≤ 200. If still over, iterate: pull more
   content into `.claude/docs/`.
7. **Never delete** user content — only relocate. If unsure where something
   belongs, default to `.claude/docs/`.

## Output

- Updated `CLAUDE.md` (≤ 200 lines).
- New files under `.claude/docs/` and `.claude/rules/`.
- Print a summary: line count before/after, list of files created.
