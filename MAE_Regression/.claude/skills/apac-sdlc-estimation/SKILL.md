---
name: apac-sdlc-estimation
description: "Break an epic, story, or spec into concrete tasks with size estimates. Outputs a task list with effort (t-shirt or points), dependencies, and risk flags."
trigger: /apac-sdlc-estimation
---

# /estimation

Decompose a feature or epic into engineering tasks with size estimates and dependency order.

## Usage

```
/estimation "<feature name>"                  # from inline prompt
/estimation --from <spec.md>                  # from a spec or requirements file
/estimation --jira <KEY-123>                  # pull epic + linked issues from Jira
/estimation --confluence <page-url>           # pull from Confluence page
/estimation --linear <TEAM-123>               # pull from Linear ticket
/estimation --github <owner/repo#123>         # pull from GitHub issue
/estimation --brd <file>                      # BRD doc (.pdf .docx .md)
/estimation --repo <path>                     # scan repo to ground sizing in real code
/estimation --scale tshirt                    # XS / S / M / L / XL (default)
/estimation --scale points                    # Fibonacci story points
/estimation --scale hours                     # raw hour ranges
/estimation --team <n>                        # parallelize for n engineers
/estimation --push-jira                       # create child issues in source Jira epic
```

Multiple sources allowed. Skill merges scope, dedupes overlap. Use MCP (Atlassian, Linear, GitHub) when configured.

## Output Format

```markdown
## Sources
- Jira: PROJ-100 (epic), PROJ-101..104 (stories)
- Spec: docs/spec-payment-v2.md
- Repo: services/payments

## Summary
- Total: <size or range>
- Critical path: <task-id → task-id>
- Parallelizable: <count> tracks

## Tasks
| ID | Task | Size | Depends On | Risk | Notes |
|----|------|------|------------|------|-------|
| T1 | <action verb + outcome> | S | — | low | |
| T2 | … | M | T1 | med | unclear API |

## Risks
- <risk> — impact / likelihood / mitigation

## Assumptions
- <what we assumed to size this>

## Unknowns
- <question that would change the estimate>
```

## Sizing Scales

| Scale | Values | Use When |
|-------|--------|----------|
| t-shirt | XS, S, M, L, XL | Pre-grooming, rough order of magnitude |
| points | 1, 2, 3, 5, 8, 13 | Sprint planning, velocity tracking |
| hours | 1–2h, 2–4h, 0.5d, 1d, 2d+ | Concrete short tasks |

## Rules

- One task = one PR-sized unit of work. If a task feels > L / 8 points, split it.
- Every task uses action verb: "Add", "Wire", "Migrate", "Validate" — never "Work on X".
- **Dependencies explicit**. No hidden ordering; reviewers see critical path.
- Flag **Risk** per task: unclear scope, external blockers, unknown perf cost.
- List **Assumptions** and **Unknowns** separately — unknowns block confident sizing.
- Pull source of truth from [[requirement]] output; do not re-derive scope here.
- No padding. State the estimate, then list what would change it.
- **Cite source** per task (ticket id, spec section). Tasks without source → unknown scope, flag risk.
- `--repo` mode: cross-check tasks against actual file paths / modules. Flag if touched area is unfamiliar.
- `--push-jira` only after user review. Never auto-create tickets unprompted.
