#!/usr/bin/env bash
# detect.sh — classify the project as brownfield (existing code) or greenfield (no code yet),
# print the result, and log the run. Does NOT write any state file — the brownfield-detect skill
# reads MODE from this script's output and acts on it in-session.
#
# Usage: detect.sh
#   stdout:  MODE=brownfield|greenfield
#            EVIDENCE=<comma-separated markers, or "none">
#            plus a human-readable summary line
#   logs via extension-log.sh:  brownfield-detect before_constitution done "mode=<mode> evidence=<...>"
#
# Markers match the brownfield auto-draft scan in the apac-sdlc-speckit-brownfield-detect skill.
#
# Graceful degradation: any failure exits 0 — detection must never block /constitution.

set -uo pipefail

find_project_root() {
  local dir="$PWD"
  while [ "${dir}" != "/" ]; do
    [ -d "${dir}/.specify" ] && echo "${dir}" && return
    dir="$(dirname "${dir}")"
  done
  echo ""
}

PROJECT_ROOT="$(find_project_root)"
[ -z "${PROJECT_ROOT}" ] && PROJECT_ROOT="$PWD"

LOGGER="${PROJECT_ROOT}/.specify/scripts/bash/extension-log.sh"

# --- Detection ---------------------------------------------------------------
MODE="greenfield"
EVIDENCE=""

note() { EVIDENCE="${EVIDENCE}${EVIDENCE:+,}$1"; }

# Manifest / build files
for f in package.json pyproject.toml go.mod Cargo.toml pom.xml build.gradle requirements.txt Makefile; do
  if [ -f "${PROJECT_ROOT}/${f}" ]; then MODE="brownfield"; note "${f}"; fi
done

# Visual Studio solution files
if ls "${PROJECT_ROOT}"/*.sln >/dev/null 2>&1; then MODE="brownfield"; note "*.sln"; fi

# Non-empty source directories
for d in src app lib cmd pkg backend frontend; do
  if [ -d "${PROJECT_ROOT}/${d}" ] && [ -n "$(ls -A "${PROJECT_ROOT}/${d}" 2>/dev/null)" ]; then
    MODE="brownfield"; note "${d}/"
  fi
done

# Substantive README (more than ~200 bytes of content)
if [ -f "${PROJECT_ROOT}/README.md" ]; then
  size="$(wc -c < "${PROJECT_ROOT}/README.md" 2>/dev/null | tr -d ' ')"
  if [ -n "${size}" ] && [ "${size}" -gt 200 ]; then MODE="brownfield"; note "README.md"; fi
fi

[ -z "${EVIDENCE}" ] && EVIDENCE="none"

# --- Log ---------------------------------------------------------------------
if [ -f "${LOGGER}" ]; then
  bash "${LOGGER}" brownfield-detect before_constitution done "mode=${MODE} evidence=${EVIDENCE}" 2>/dev/null || true
fi

# --- Output (parsed by the skill) -------------------------------------------
echo "MODE=${MODE}"
echo "EVIDENCE=${EVIDENCE}"
echo "[brownfield-detect] ${MODE} (evidence: ${EVIDENCE})"

exit 0
