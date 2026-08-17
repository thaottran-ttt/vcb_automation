## Speckit SDD Workflow

Speckit has been added to this project. All new features should be built using the
Spec-Driven Development (SDD) workflow: Constitution → Specify → Plan → Tasks → Analyze → Implement.

Existing code is untouched. Speckit operates alongside whatever workflows were
already in place.

### Overview

Every new feature follows this sequence:

```
/apac-sdlc-speckit-constitution  →  /apac-sdlc-speckit-specify  →  /apac-sdlc-speckit-clarify (optional)
→  /apac-sdlc-speckit-plan  →  /apac-sdlc-speckit-tasks  →  /apac-sdlc-speckit-analyze  →  /apac-sdlc-speckit-implement
```

There are two human review gates: after Specify (before planning) and after Analyze
(before implementation). Never skip them.

### Commands

| Command | When to Use | What It Does |
|---------|-------------|--------------|
| `/apac-sdlc-speckit-constitution` | Once at project start, then when constraints change | Defines project principles, stack, architecture, and behavioral rules |
| `/apac-sdlc-speckit-specify` | Start of every feature | Generates a detailed feature spec (spec.md) from your description |
| `/apac-sdlc-speckit-clarify` | After specify, when requirements are ambiguous | Asks targeted questions to resolve ambiguity before planning |
| `/apac-sdlc-speckit-plan` | After spec is approved | Generates a technical plan (plan.md): architecture, data model, API design, phases |
| `/apac-sdlc-speckit-tasks` | After plan is approved | Breaks the plan into atomic, verifiable tasks (tasks.md) with effort and dependencies |
| `/apac-sdlc-speckit-analyze` | After tasks are generated | Cross-artifact analysis: checks spec/plan/tasks for gaps, ambiguity, and constitution violations |
| `/apac-sdlc-speckit-implement` | After analysis is approved | Executes tasks one by one, verifying each before proceeding |

### Running the Full Cycle

To run the full automated workflow:

```
speckit run speckit --spec "describe what you want to build"
```

Or run commands individually for more control.

### Where Files Live

```
.specify/
  memory/
    constitution.md        # Project principles — fill in Sections I–VIII before first use
  templates/
    spec-template.md       # Reference template for specs
    plan-template.md       # Reference template for plans
    tasks-template.md      # Reference template for task lists
    checklist-template.md  # Definition of Done checklist
  workflows/
    speckit/workflow.yml   # Full SDD cycle definition
  scripts/bash/            # Helper scripts used by skill commands
  feature.json             # Current feature metadata
  integration.json         # Active AI integration config

.claude/
  skills/apac-sdlc-speckit/          # Slash command skill definitions
```

### Constitution

The project constitution lives at `.specify/memory/constitution.md`.

**Before running `/apac-sdlc-speckit-specify` for the first time:**
Fill in Sections I–VIII with this project's specific context: technology stack,
architecture principles, code style, testing standards, security model, performance
targets, and definition of done. Use the existing codebase as your source of truth
for what's already established.

Section IX (Behavioral Principles) is pre-filled and must not be modified.

**Section IX always applies.** Every implementation decision must respect:
1. Think Before Coding — state assumptions, surface tradeoffs, ask when unclear
2. Simplicity First — minimum code that solves the problem, nothing speculative
3. Surgical Changes — touch only what you must, match existing style
4. Goal-Driven Execution — define success criteria before writing a line of code

---

### Auto-setup rule

If `.specify/memory/constitution.md` exists and **all** of Sections I–VIII still contain
only HTML comment placeholders (`<!-- ... -->`), run `/apac-sdlc-speckit-constitution`
immediately before doing any other work in this project.

---

### Knowledge Base Integration

If a project knowledge base (llm-wiki, graphify, or others) is set up with `apac-sdlc add knowledge`,
its query instructions are stored in `.specify/kb/`. The `/specify`, `/plan`, and `/clarify` skills
automatically detect and query registered knowledge bases when they run — no manual sync required.

To add a knowledge base after SDD is already set up:

```bash
apac-sdlc add knowledge --pattern=llm-wiki   # or --pattern=graphify
```

This creates `.specify/kb/<pattern>.md` with the detection signal and query approach for that KB.
The next time any spec-kit skill runs, it will pick up the new KB automatically.
