---
name: apac-sdlc-requirement
description: "Turn a rough feature idea into structured user stories with acceptance criteria. Produces INVEST-aligned stories, Gherkin scenarios, and explicit non-goals ready for backlog grooming."
trigger: /apac-sdlc-requirement
---

# /requirement

Convert a feature idea, problem statement, or stakeholder note into user stories with acceptance criteria.

## Usage

```
/requirement "<one-line idea>"           # from inline prompt
/requirement --file <path>               # local brief / notes / BRD (.md .pdf .docx)
/requirement --jira <KEY-123>            # pull from Jira ticket (description + comments)
/requirement --confluence <page-url>     # pull from Confluence page
/requirement --linear <TEAM-123>         # pull from Linear ticket
/requirement --github <owner/repo#123>   # pull from GitHub issue
/requirement --notion <page-url>         # pull from Notion page
/requirement --url <url>                 # any web doc
/requirement --transcript <file>         # discovery call / meeting notes
/requirement --persona <role>            # force a primary persona
/requirement --format gherkin            # output Given/When/Then
/requirement --format jira               # output Jira-ready blocks
```

Combine sources: `--jira PROJ-12 --confluence <url> --transcript notes.md`. Skill merges, dedupes, flags conflicts as **Open Questions**.

## Source Handling

| Source | What gets extracted |
|--------|---------------------|
| Jira / Linear / GitHub issue | summary, description, acceptance criteria field, comments, linked tickets |
| Confluence / Notion | headings, requirement tables, decision logs |
| BRD (.pdf/.docx/.md) | business objective, scope, stakeholders, success metrics |
| Transcript | stated pain points, requested capabilities, attributed quotes |
| URL | main content; ignores nav/footer |

Use MCP integrations when available (Atlassian, Linear, Notion, GitHub). Fall back to CLI (`gh issue view`, `jira issue view`) or browser fetch.

## Output Format

```markdown
## Feature
<short name>

## Sources
- Jira: PROJ-123
- BRD: docs/brd-v2.pdf §3.2
- Transcript: 2026-05-10 stakeholder call

## Problem
<who hurts, what hurts, evidence — cite source>

## Goal
<measurable outcome>

## Non-Goals
- <out of scope item>

## Personas
- <role> — <context, motivation>

## User Stories
1. As a <persona>, I want <capability>, so that <benefit>.
   **Acceptance Criteria**
   - [ ] Given <state>, when <action>, then <result>
   - [ ] ...
   **Source:** PROJ-123 comment #4

## Open Questions
- <question for stakeholder> (conflict: BRD §2 vs Jira comment)
```

## Rules

- INVEST per story: Independent, Negotiable, Valuable, Estimable, Small, Testable.
- Acceptance criteria testable and binary — no "should be fast", use "p95 < 200ms".
- Surface ambiguity as **Open Questions**, not assumptions buried in criteria.
- **Non-Goals** mandatory — explicit out-of-scope list prevents scope creep.
- No solutioning. Describe *what* and *why*, not *how*.
- One story = one user-visible behavior. Split CRUD into separate stories if flows differ.
- **Cite source** per story / criterion (ticket id, BRD section, transcript timestamp). Untraceable claims → **Open Question**.
- Source conflicts never silently resolved. Surface both, ask stakeholder.
