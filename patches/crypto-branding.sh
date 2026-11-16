#!/usr/bin/env bash
set -euo pipefail
APP="app_mojo"
GRADLE="$APP/build.gradle"
VALUES="$APP/src/main/res/values"
[ -f "$GRADLE" ] || { echo "not mojo tree"; exit 1; }

# applicationId ONLY (namespace не трогать)
sed -i 's/applicationId "git.artdeell.mjlaunch"/applicationId "ru.cryptolauncher"/g' "$GRADLE"
sed -i "s/git.artdeell.mjlaunch.debug/ru.cryptolauncher.debug/g" "$GRADLE"
sed -i "s/git.artdeell.mjlaunch.scoped/ru.cryptolauncher.scoped/g" "$GRADLE"
sed -i "s/'git.artdeell.mjlaunch'/'ru.cryptolauncher'/g" "$GRADLE"
sed -i "s/'git.artdeell.mjlaunch.debug'/'ru.cryptolauncher.debug'/g" "$GRADLE"

# имена
find "$APP/src/main/res" -name "strings.xml" | while read -r f; do
  sed -i 's/MJLauncher/Crypto Launcher/g' "$f"
  sed -i 's/MojoLauncher/Crypto Launcher/g' "$f"
  sed -i 's/Mojo Launcher/Crypto Launcher/g' "$f"
done
if [ -f "$VALUES/strings.xml" ]; then
  sed -i 's/<string name="app_short_name">[^<]*</<string name="app_short_name">Crypto</g' "$VALUES/strings.xml" || true
fi

# тёмно-синяя палитра
mkdir -p "$VALUES"
cat > "$VALUES/crypto_colors.xml" << 'XML'
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="crypto_bg">#FF0A0E14</color>
    <color name="crypto_surface">#FF121826</color>
    <color name="crypto_primary">#FF1B4D8C</color>
    <color name="crypto_accent">#FF2B6CB0</color>
    <color name="crypto_button">#FF163A66</color>
    <color name="crypto_text">#FFE8EEF7</color>
</resources>
XML

# cyan/blue Mojo → Crypto dark blue
find "$APP/src/main/res" -type f \( -name "*.xml" \) | while read -r f; do
  sed -i 's/#FF00BCD4/#FF1B4D8C/gi' "$f" || true
  sed -i 's/#FF03A9F4/#FF2B6CB0/gi' "$f" || true
  sed -i 's/#FF2196F3/#FF1B4D8C/gi' "$f" || true
  sed -i 's/#FF00A0E3/#FF163A66/gi' "$f" || true
  sed -i 's/#FF00AAFF/#FF1B4D8C/gi' "$f" || true
done

echo "OK: Crypto UI brand"
