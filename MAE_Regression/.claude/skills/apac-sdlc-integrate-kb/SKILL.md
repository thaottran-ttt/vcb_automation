---
name: "apac-sdlc-integrate-kb"
description: "Connect one knowledge base pattern (llm-wiki, graphify, or rag) to spec-kit. If only one KB is installed, wires it automatically. If multiple are installed, asks you to pick one — spec-kit queries exactly one KB per project."
argument-hint: "Optional: pass the pattern name directly (llm-wiki | graphify | rag) to skip the selection prompt"
compatibility: "Requires spec-kit project structure with .specify/ directory"
metadata:
  author: "apac-sdlc-framework"
user-invocable: true
disable-model-invocation: false
---

## User Input

```text
$ARGUMENTS
```

If the user passed a pattern name, skip detection and go straight to Step 3 with that pattern.

---

## Step 1 — Verify spec-kit is installed

Check that `.specify/memory/constitution.md` exists in the project root.

If it does **not** exist, stop and tell the user:
> spec-kit is not installed in this project. Run `apac-sdlc add sdd` first, then re-run this skill.

---

## Step 2 — Detect installed KB patterns

Check each pattern using its filesystem detection signal:

| Pattern | Detection signal |
|---------|-----------------|
| `llm-wiki` | `llm-wiki/index.md` exists at project root |
| `graphify` | `.graphifyignore` exists at project root |
| `rag` | `.mcp.json` exists at project root AND contains `"rag-mcp-server"` under `mcpServers` |

Build a list of all detected patterns.

**If 0 detected:**
Tell the user no KB pattern is installed and list what each signal looks like. Suggest:
```
apac-sdlc add <name> --pattern llm-wiki   # LLM-maintained wiki
apac-sdlc add <name> --pattern graphify   # knowledge graph
apac-sdlc add <name> --pattern rag        # RAG server
```
Stop here.

---

## Step 3 — Choose which KB to integrate

**spec-kit uses exactly one KB per project.**

**If exactly 1 pattern detected:**
Use it directly. Tell the user:
> Integrating `<pattern>` with spec-kit...

**If 2+ patterns detected:**
Present a numbered selection list:

```
Multiple KB patterns found. spec-kit queries exactly one KB.
Which KB should /specify, /plan, and /clarify use?

  1. llm-wiki
  2. rag
  (etc.)

Enter the number or pattern name:
```

Wait for the user's selection. If the input is invalid, ask again.

---

## Step 4 — Wire the chosen KB

1. Create `.specify/kb/` directory if it does not exist.

2. Write `.specify/kb/<chosen>.md` with the content from the template below for the chosen pattern.
   If the file already exists, overwrite it (in case the user is re-integrating after content changed).

3. **Remove any other `.md` files** in `.specify/kb/` that belong to a different pattern
   (i.e., delete `llm-wiki.md`, `graphify.md`, or `rag.md` if they exist and were NOT the chosen one).
   This ensures only one KB is active.

### Template content for `.specify/kb/llm-wiki.md`

```
# KB: llm-wiki

Detection: `llm-wiki/index.md` exists at the project root.

Query:
1. Read `llm-wiki/index.md` to get the full catalog of entities, concepts, and summaries.
   Scale guard: if the file has more than ~50 entries, scan only the **Entities** and
   **Concepts** sections — skip Summaries and Analyses.
2. From the current feature context, identify 3–5 most relevant entries
   (match by entry name, type, and one-line description).
3. If no relevant entries found, stop here — skip silently.
4. Read each identified page from `llm-wiki/<page-name>.md`.
5. Use this context when drafting: reference known entities by their documented names,
   respect patterns and decisions already captured, surface contradictions between the
   feature request and existing documented constraints.
```

### Template content for `.specify/kb/graphify.md`

```
# KB: graphify

Detection: `graphify-out/` directory exists at the project root.

Query:
1. If `graphify-out/GRAPH_REPORT.md` exists, read it for key project insights.
2. Extract 2–4 key concepts from the current feature context and run:
   `graphify query "<key concepts>"`
   If the command is not on PATH or fails for any reason, skip silently.
3. Use the results to understand which components and relationships this feature touches.
```

### Template content for `.specify/kb/rag.md`

```
# KB: rag-server

Detection: `.mcp.json` exists at the project root and contains a `rag-mcp-server` entry.

Query:
1. Call the `rag_query` MCP tool with the feature description as the query,
   using mode="hybrid".
2. Use the returned result to understand which codebase components are relevant
   to this feature; surface any architectural decisions or constraints found.
3. If the `rag_query` tool is unavailable or returns an error, skip silently.
```

---

## Step 5 — Report

```
KB integration complete.

  Active KB:  <chosen pattern>  (.specify/kb/<chosen>.md)
  Removed:    <list of stale KB files deleted, or "none">

/specify, /plan, and /clarify will now query <chosen pattern> for each feature.
```
