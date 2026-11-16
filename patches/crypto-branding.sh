#!/usr/bin/env bash
set -euo pipefail
APP_GRADLE="app_mojo/build.gradle"
STRINGS="app_mojo/src/main/res/values/strings.xml"
[ -f "$APP_GRADLE" ] || { echo "missing $APP_GRADLE"; exit 1; }

# НЕ трогать namespace — иначе ломается R
sed -i 's/applicationId "git.artdeell.mjlaunch"/applicationId "ru.cryptolauncher"/g' "$APP_GRADLE"
sed -i "s/git.artdeell.mjlaunch.debug/ru.cryptolauncher.debug/g" "$APP_GRADLE"
sed -i "s/git.artdeell.mjlaunch.scoped/ru.cryptolauncher.scoped/g" "$APP_GRADLE"
sed -i "s/'git.artdeell.mjlaunch'/'ru.cryptolauncher'/g" "$APP_GRADLE"
sed -i "s/'git.artdeell.mjlaunch.debug'/'ru.cryptolauncher.debug'/g" "$APP_GRADLE"

if [ -f "$STRINGS" ]; then
  sed -i 's/MJLauncher/Crypto Launcher/g' "$STRINGS"
fi
echo "OK: branding without namespace change"
