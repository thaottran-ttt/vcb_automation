---
name: apac-sdlc-security-review
description: "OWASP-focused security audit of changed or specified files. Identifies injection, auth, secrets, and dependency risks with severity ratings."
trigger: /apac-sdlc-security-review
---

# /security-review

Audit code for security vulnerabilities. Covers the OWASP Top 10 and common implementation mistakes.

## Usage

```
/security-review                # audit staged git changes
/security-review <file>         # audit a specific file
/security-review <dir>          # audit all files in a directory
/security-review --owasp        # print OWASP category tags per finding
/security-review --deps         # also check package.json / requirements.txt for known-vuln packages
```

## Checks Performed

| Category | Examples |
|----------|---------|
| **Injection** | SQL injection, command injection, LDAP injection, template injection |
| **Broken Auth** | Hardcoded credentials, weak tokens, missing expiry, insecure session handling |
| **Sensitive Data** | Secrets in code, unencrypted PII, logging sensitive fields |
| **XSS** | Unescaped user input rendered in HTML, dangerouslySetInnerHTML |
| **Insecure Deserialization** | Untrusted `eval`, `pickle.loads`, `JSON.parse` on user data |
| **SSRF / Path Traversal** | Unvalidated URLs in fetch calls, `../` in file paths |
| **Dependency Risk** | Known CVEs in direct dependencies (with `--deps`) |
| **Cryptography** | MD5/SHA1 for passwords, weak random, missing TLS verification |

## Output Format

```
[CRITICAL|HIGH|MEDIUM|LOW] file.ts:88 — description
  OWASP: A03:2021 – Injection
  Fix: specific remediation guidance
```

All findings sorted by severity. Summary: `N critical, M high, K medium, J low.`

## Non-Goals

- Does not replace a full SAST scan (use Snyk, Semgrep, or CodeQL for CI)
- Does not audit infrastructure/IaC unless files are in scope
