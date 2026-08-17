---
name: apac-sdlc-code-review
description: "Review code for bugs, logic errors, style issues, and best practices. Works on staged changes, specific files, or entire directories."
trigger: /apac-sdlc-code-review
---

# /code-review

Review code for correctness, clarity, and maintainability. Covers logic bugs, naming, duplication, complexity, and adherence to project conventions.

## Usage

```
/code-review                    # review staged git changes
/code-review <file>             # review a specific file
/code-review <dir>              # review all files in a directory
/code-review --strict           # fail on warnings, not just errors
/code-review --fix              # apply safe auto-fixes after review
```

## What Gets Checked

- **Bugs**: off-by-one errors, null dereferences, incorrect conditionals, unreachable code
- **Logic**: incorrect algorithmic behavior, missing edge cases, wrong assumptions
- **Clarity**: unclear naming, overly complex expressions, misleading comments
- **Duplication**: repeated logic that should be extracted
- **Conventions**: style inconsistencies with the surrounding codebase

## What Gets Skipped

- Auto-generated files
- Vendored/third-party code
- Test fixtures and mocks (unless `--include-tests` passed)

## Output Format

For each issue found:

```
[SEVERITY] file.ts:42 — description of the problem
  Suggestion: what to do instead
```

Severities: `ERROR` (must fix), `WARN` (should fix), `INFO` (consider).

Summary line at end: `N errors, M warnings across K files.`
