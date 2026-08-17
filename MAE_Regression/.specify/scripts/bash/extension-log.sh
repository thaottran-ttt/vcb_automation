#!/usr/bin/env bash
# extension-log.sh — append one structured (JSONL) entry to the speckit extension log.
#
# Usage: extension-log.sh <extension> <event> <status> [detail]
#   extension : extension id      (e.g. kb-context, brownfield-detect)
#   event     : hook event name   (e.g. before_specify, before_clarify, before_constitution)
#   status    : start | done | skipped | error
#   detail    : optional free-text note
#
# Writes to <project>/.specify/logs/extensions.log so you can confirm an extension ran:
#   {"ts":"2026-06-04T07:13:02Z","extension":"kb-context","event":"before_specify","status":"done","detail":"rag: 4 items"}
#
# Graceful degradation: any failure exits 0 — logging must never block a speckit command.

set -uo pipefail

EXT="${1:-unknown}"
EVENT="${2:-unknown}"
STATUS="${3:-unknown}"
DETAIL="${4:-}"

# Locate project root (nearest ancestor containing .specify/) — same pattern as auto-commit.sh
find_project_root() {
  local dir="$PWD"
  while [ "${dir}" != "/" ]; do
    [ -d "${dir}/.specify" ] && echo "${dir}" && return
    dir="$(dirname "${dir}")"
  done
  echo ""
}

PROJECT_ROOT="$(find_project_root)"
[ -z "${PROJECT_ROOT}" ] && exit 0

LOG_DIR="${PROJECT_ROOT}/.specify/logs"

mkdir -p "${LOG_DIR}" 2>/dev/null || exit 0

# Self-ignore: keep the log out of git so the git auto-commit extension's `git add .`
# never commits it. Written once.
[ -f "${LOG_DIR}/.gitignore" ] || printf '*\n' > "${LOG_DIR}/.gitignore" 2>/dev/null || true

# Minimal JSON string escaping: backslash, double-quote, then strip CR/LF/tab to spaces.
json_escape() {
  local s="$1"
  s="${s//\\/\\\\}"   # \  -> \\
  s="${s//\"/\\\"}"   # "  -> \"
  s="${s//$'\t'/ }"   # tab -> space
  s="${s//$'\r'/ }"   # CR  -> space
  s="${s//$'\n'/ }"   # LF  -> space
  printf '%s' "$s"
}

TS="$(date -u +%Y-%m-%dT%H:%M:%SZ 2>/dev/null)" || TS=""

printf '{"ts":"%s","extension":"%s","event":"%s","status":"%s","detail":"%s"}\n' \
  "$(json_escape "${TS}")" \
  "$(json_escape "${EXT}")" \
  "$(json_escape "${EVENT}")" \
  "$(json_escape "${STATUS}")" \
  "$(json_escape "${DETAIL}")" \
  >> "${LOG_DIR}/extensions.log" 2>/dev/null || exit 0

exit 0
