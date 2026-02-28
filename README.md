# Atlin JetBrains Plugin

Full language support for the `.atlin` key-value data format inside JetBrains IDEs (PhpStorm, IntelliJ IDEA, etc.).

> For more information about the Atlin data format, see the [official article](https://medium.com/p/8e071ae3af21).

---

## Installation

### Via JetBrains Marketplace (Recommended)
1. Open your JetBrains IDE
2. Go to **Settings → Plugins → Marketplace**
3. Search for **Atlin**
4. Click **Install** and restart the IDE

Or install directly from the Marketplace page:
👉 [https://plugins.jetbrains.com/plugin/atlin](https://plugins.jetbrains.com/plugin/atlin)

### Via GitHub Releases (Manual)
1. Go to the [Releases](https://github.com/nabeghe/atlin-jetbrains-plugin/releases) page and download the latest `.zip` file
2. Open your JetBrains IDE
3. Go to **Settings → Plugins → ⚙️ → Install Plugin from Disk...**
4. Select the downloaded `.zip` file
5. Restart the IDE

---

## Features

- Syntax highlighting for keys, values, and comments
- Background highlight for separator (blank line before a key)
- Multiline values and Emoji support
- Configurable key prefix (default `@`, changeable to any character)
- Code folding for multiline values
- Autocomplete for existing keys in the file
- Validation (error for empty key name, warning for wrong prefix)
- Customizable color scheme via Settings

---

## Atlin Format

```
# This is a comment

@name
John Doe

@bio
A software developer.
Loves clean code. ❤️

@email
john@example.com

@skills
PHP, Java, JavaScript
```

### Rules

- Lines starting with `@` (or the configured prefix) → **Key**
- Lines after a key until the next blank line → **Value** (can be multiline)
- A completely empty line (no characters at all) before a key → **Separator**
- Lines starting with `#` → **Comment** (ignored)

> **Separator rule:** Comment lines are invisible — if there are comments between
> a blank line and a key, the blank line is still treated as the separator.
> Example: `blank line` → `# comment` → `@key` — the blank line is the separator.

---

## Change Key Prefix

Default is `@`. To change it to `|` or any other character:

1. Go to **Settings → Editor → Atlin**
2. Change the **Key Prefix Character** field
3. Click **Apply**

Highlighting and validation update immediately based on the new character.

---

## Change Colors

Go to **Settings → Editor → Color Scheme → Atlin**:

| Element | Description | Default |
|---|---|---|
| **Key** | Lines starting with `@` | Bold purple |
| **Value** | Text values | Green |
| **Separator (blank line)** | Empty line before a key | Dark green background |
| **Comment** | Lines starting with `#` | Grey italic |

> To restore plugin defaults: click ⚙️ and select **Restore Defaults**.

---

## FAQ

**Colors are not applied?**
Go to Settings → Editor → Color Scheme → Atlin, click ⚙️ and select **Restore Defaults**.

**`.atlin` extension is not recognized?**
Go to Settings → Editor → File Types → Atlin and add the `atlin` extension manually.

**Highlighting does not update after changing Key Prefix?**
Close the file and reopen it.

---

## 📖 License

Licensed under the MIT license, see [LICENSE.md](LICENSE.md) for details.