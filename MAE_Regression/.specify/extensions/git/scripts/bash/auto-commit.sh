#!/usr/bin/env bash
# auto-commit.sh — stage and commit changes for a given speckit hook event
#
# Usage: auto-commit.sh <event_name>
#   event_name: after_specify | after_plan | after_tasks | after_analyze |
#               after_implement | after_constitution | before_*

set -euo pipefail

EVENT="${1:-}"
if [ -z "${EVENT}" ]; then
  echo "[speckit-git-commit] No event name provided. Usage: auto-commit.sh <event_name>" >&2
  exit 1
fi

# Locate project root (directory containing .specify/)
find_project_root() {
  local dir="$PWD"
  while [ "${dir}" != "/" ]; do
    [ -d "${dir}/.specify" ] && echo "${dir}" && return
    dir="$(dirname "${dir}")"
  done
  echo ""
}

PROJECT_ROOT="$(find_project_root)"
if [ -z "${PROJECT_ROOT}" ]; then
  echo "[speckit-git-commit] Could not find .specify/ directory. Skipping." >&2
  exit 0
fi

CONFIG="${PROJECT_ROOT}/.specify/extensions/git/git-config.yml"

# Graceful degradation: no config → disabled by default
if [ ! -f "${CONFIG}" ]; then
  echo "[speckit-git-commit] No git-config.yml found. Auto-commit disabled." >&2
  exit 0
fi

# Graceful degradation: git not available
if ! command -v git &>/dev/null; then
  echo "[speckit-git-commit] git not found. Skipping." >&2
  exit 0
fi

# Graceful degradation: not a git repository
if ! git -C "${PROJECT_ROOT}" rev-parse --git-dir &>/dev/null 2>&1; then
  echo "[speckit-git-commit] Not a git repository. Skipping." >&2
  exit 0
fi

# Parse git-config.yml (minimal YAML parser — no external dependencies)
# Reads: auto_commit.default, auto_commit.<event>.enabled, auto_commit.<event>.message
parse_yaml_value() {
  local file="$1"
  local key="$2"
  grep -A1 "^  ${key}:" "${file}" 2>/dev/null | grep "enabled:" | awk '{print $2}' | tr -d '"'
}

parse_yaml_message() {
  local file="$1"
  local key="$2"
  grep -A2 "^  ${key}:" "${file}" 2>/dev/null | grep "message:" | sed 's/.*message: *//' | tr -d '"'
}

parse_yaml_default() {
  local file="$1"
  grep "^  default:" "${file}" 2>/dev/null | awk '{print $2}' | tr -d '"'
}

# Determine enabled state
DEFAULT_ENABLED="$(parse_yaml_default "${CONFIG}")"
EVENT_ENABLED="$(parse_yaml_value "${CONFIG}" "${EVENT}")"

ENABLED="${EVENT_ENABLED:-${DEFAULT_ENABLED:-false}}"

if [ "${ENABLED}" != "true" ]; then
  echo "[speckit-git-commit] Auto-commit disabled for event '${EVENT}'. Skipping."
  exit 0
fi

# Determine commit message
DEFAULT_MSG="[Spec Kit] ${EVENT}"
EVENT_MSG="$(parse_yaml_message "${CONFIG}" "${EVENT}")"
COMMIT_MSG="${EVENT_MSG:-${DEFAULT_MSG}}"

# Check for uncommitted changes
cd "${PROJECT_ROOT}"
if git diff --quiet && git diff --staged --quiet && [ -z "$(git ls-files --others --exclude-standard)" ]; then
  echo "[speckit-git-commit] No changes to commit."
  exit 0
fi

# Stage and commit
git add .
git commit -m "${COMMIT_MSG}"
echo "[speckit-git-commit] Committed: ${COMMIT_MSG}"
