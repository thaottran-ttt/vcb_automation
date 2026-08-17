---
name: "apac-sdlc-speckit"
description: "Spec-Driven Development (SDD) workflow skills for Claude Code. Provides the full Constitution → Specify → Clarify → Plan → Tasks → Analyze → Implement cycle."
compatibility: "Requires spec-kit project structure with .specify/ directory"
metadata:
  author: "github-spec-kit"
  version: "1.0.0"
---

# Speckit SDD Skills

Available slash commands:

| Command | Description | When to use |
|---|---|---|
| `/apac-sdlc-speckit-constitution` | Initialize or update the project constitution | Once per project, or when principles need updating |
| `/apac-sdlc-speckit-specify` | Write a feature specification | Start of every feature |
| `/apac-sdlc-speckit-clarify` | Resolve ambiguities in a spec | On-demand, between specify and plan |
| `/apac-sdlc-speckit-plan` | Create a technical architecture plan | After spec is approved |
| `/apac-sdlc-speckit-tasks` | Break plan into atomic, actionable tasks | After plan is approved |
| `/apac-sdlc-speckit-analyze` | Cross-artifact consistency analysis (read-only) | After tasks, before implement |
| `/apac-sdlc-speckit-implement` | Execute implementation of tasks | After analysis passes |

## Workflow

Full automated cycle: `/apac-sdlc-speckit-specify` → review → `/apac-sdlc-speckit-plan` → review → `/apac-sdlc-speckit-tasks` → `/apac-sdlc-speckit-analyze` → review → `/apac-sdlc-speckit-implement`

On-demand: `/apac-sdlc-speckit-clarify` (anytime), `/apac-sdlc-speckit-constitution` (project setup)

## Constitution

The project constitution at `.specify/memory/constitution.md` is authoritative. All skills respect it. Section IX (Behavioral Principles) is non-negotiable and pre-filled on bootstrap.
