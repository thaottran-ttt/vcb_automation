# How to Fill In Your Project Constitution

<!-- This is a GUIDE, not the constitution itself.
     The constitution lives at: .specify/memory/constitution.md
     Open that file and fill in each section using the guidance below. -->

---

## What the Constitution Is

The constitution is the single source of truth for how this project is built. Every
spec, plan, task, and implementation decision is checked against it. Fill it in
before running `/apac-sdlc-speckit-specify` for the first time.

**Sections I–VIII:** Project-specific. You fill these in.
**Section IX:** Pre-filled. Do not modify.

---

## Section I — Project Overview

**What to write:** A plain-English description of the project — what it is, who uses it,
and what problem it solves.

**Guiding questions:**
- What is the one-sentence pitch for this project?
- Who are the primary users (internal team, external customers, other services)?
- What does success look like at launch? In six months?
- Is there an existing system this replaces, or is this net-new?

**Good example:**
```
A REST API that lets field technicians log equipment inspections from mobile devices.
Replaces paper forms and manual data entry into a legacy spreadsheet system.
Primary users: ~200 field technicians, 10 supervisors, and 2 data analysts.
```

**Antipattern to avoid:** Vague mission statements ("be the best platform"). Write
what it actually does.

---

## Section II — Technology Stack

**What to write:** Every technology decision that constrains implementation. Be specific
about versions when they matter.

**Guiding questions:**
- What language and runtime? (e.g., Python 3.12, Node 20 LTS, Go 1.22)
- What web framework? (e.g., FastAPI, Express, Gin)
- What database(s) and how do you access them? (e.g., PostgreSQL 15 via SQLAlchemy)
- Where does this run? (e.g., AWS ECS, Heroku, bare metal)
- What external services integrate with this? (e.g., Auth0, Stripe, Twilio)
- What build/package tools? (e.g., pnpm, Poetry, Maven)

**Tip:** If a dependency is optional or under evaluation, say so explicitly. "We might
use Redis" is better than silent ambiguity.

---

## Section III — Architecture Principles

**What to write:** The structural rules that define how the codebase is organized and
how components interact.

**Guiding questions:**
- What is the top-level shape? (monolith, microservices, serverless, modular monolith)
- How is the code layered? (e.g., routes → controllers → services → repositories)
- What patterns are mandatory? (e.g., repository pattern for all DB access)
- What is explicitly forbidden? (e.g., no direct DB queries outside repository layer)
- How does async work? (e.g., background jobs via queue, no blocking in request thread)

**Good example:**
```
Layered: routes → handlers → services → repositories.
Services must not call other services directly. Cross-service data goes through
a shared domain interface.
No business logic in handlers. No raw SQL outside repository files.
```

---

## Section IV — Code Style & Conventions

**What to write:** Naming, formatting, and structural conventions the entire codebase
follows. The more specific, the better — AI agents will match whatever you write here.

**Guiding questions:**
- Naming: where is camelCase, PascalCase, snake_case used?
- File naming convention: kebab-case? snake_case? One class per file?
- Linter and formatter (and whether they're enforced in CI)
- Import ordering (e.g., stdlib → third-party → internal)
- Comment policy: when are inline comments required vs. noise?
- Any idioms specific to this project (e.g., always use early returns)

---

## Section V — Testing Standards

**What to write:** What must be tested, how, and with what tools. Be explicit about
minimums — vague aspirations ("good test coverage") generate vague tests.

**Guiding questions:**
- Test framework(s) (unit, integration, e2e)
- Minimum line/branch coverage threshold, if any
- What must always have tests? (e.g., all public service methods, all HTTP routes)
- What is explicitly out of scope? (e.g., don't test third-party SDK internals)
- Where do test files live? (alongside source, or in a `/tests` directory?)
- Naming convention for describe/it/test blocks

---

## Section VI — Security & Compliance

**What to write:** The security model that all code must respect. Don't leave this
blank — even internal tools have a threat model.

**Guiding questions:**
- How are users authenticated? (JWT, sessions, API keys, OAuth)
- How is authorization enforced? (RBAC, ownership checks, middleware)
- What data is sensitive and what does "sensitive" require? (no logging, encryption at rest)
- Where is input validated and with what library?
- How are secrets managed? (env vars, Vault, AWS Secrets Manager)
- Any regulatory requirements? (GDPR, HIPAA, SOC 2, PCI)

---

## Section VII — Performance Targets

**What to write:** Concrete, measurable performance requirements. "Fast" is not a
requirement. "P99 < 200ms" is.

**Guiding questions:**
- P50/P99 latency targets per endpoint category (reads vs. writes)
- Peak throughput target (req/s or concurrent users)
- Database query budget (e.g., no single query > 100ms)
- Caching strategy and what gets cached
- Any known bottlenecks or scaling constraints to design around

**If you don't have hard requirements yet:** Write your best estimates and mark them
as provisional. "~200ms P99 for reads (TBD)" is better than leaving it blank.

---

## Section VIII — Definition of Done

**What to write:** A checklist that every task must satisfy before it's considered
complete. This becomes the baseline for `/apac-sdlc-speckit-analyze` and code review.

**Guiding questions:**
- Tests written, passing, and in CI?
- Code reviewed by whom?
- What documentation must be updated?
- Do DB migrations need review? Are they reversible?
- Is there a rollback plan for risky changes?
- Is there a feature flag?
- Has performance been benchmarked for changes in hot paths?

---

## Section IX — Behavioral Principles

**Pre-filled. Do not modify.**

Section IX is shipped with every bootstrap and applies to all projects. It governs
how the AI agent thinks and writes code — not what the code looks like.

---

**Version**: [CONSTITUTION_VERSION] | **Ratified**: [RATIFICATION_DATE] | **Last Amended**: [LAST_AMENDED_DATE]
<!-- Example: Version: 2.1.1 | Ratified: 2025-06-13 | Last Amended: 2025-07-16 -->