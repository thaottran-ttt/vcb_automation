---
name: apac-sdlc-pr-description
description: "Generate a pull request title and description from staged git changes or a branch diff. Produces a structured summary ready to paste into GitHub/GitLab."
trigger: /apac-sdlc-pr-description
---

# /pr-description

Generate a structured PR title and body from the current branch diff against a base branch.

## Usage

```
/pr-description                 # diff current branch vs main
/pr-description --base develop  # diff against a different base branch
/pr-description --copy          # copy output to clipboard
/pr-description --gh            # pipe directly into gh pr create
```

## Output Format

```markdown
## Title
<concise imperative-mood summary under 70 chars>

## Summary
- What changed and why (bullet points)
- Key decisions made

## Test Plan
- [ ] How to verify this works
- [ ] Edge cases to check

## Notes
Any migration steps, breaking changes, or reviewer callouts.
```

## Guidelines Followed

- Title uses imperative mood ("Add X", "Fix Y", "Remove Z")
- Summary explains *why*, not just *what* (the diff already shows what)
- Breaking changes and migration steps are surfaced explicitly
- No filler ("This PR…", "In this change…")
