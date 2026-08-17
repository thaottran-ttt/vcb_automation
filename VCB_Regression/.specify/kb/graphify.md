# KB: graphify

Detection: `graphify-out/` directory exists at the project root.

Query:
1. If `graphify-out/GRAPH_REPORT.md` exists, read it for key project insights.
2. Extract 2–4 key concepts from the current feature context and run:
   `graphify query "<key concepts>"`
   If the command is not on PATH or fails for any reason, skip silently.
3. Use the results to understand which components and relationships this feature touches.
