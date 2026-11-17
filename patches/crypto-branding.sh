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

# --- auto features on MainMenuFragment ---
MAIN_MENU="app_mojo/src/main/java/git/artdeell/mojo/fragments/MainMenuFragment.java"
if [ -f "$MAIN_MENU" ] && ! grep -q "CRYPTO_FEATURES" "$MAIN_MENU"; then
  python3 - << 'PY'
from pathlib import Path
p = Path("app_mojo/src/main/java/git/artdeell/mojo/fragments/MainMenuFragment.java")
t = p.read_text()
if "import git.artdeell.mojo.crypto.CryptoBootstrap;" not in t:
    t = t.replace("import git.artdeell.mojo.utils.FileUtils;",
        "import git.artdeell.mojo.utils.FileUtils;\nimport git.artdeell.mojo.crypto.CryptoBootstrap;\nimport android.app.AlertDialog;\nimport android.widget.EditText;")
old = "mPlayButton.setOnClickListener(v -> ExtraCore.setValue(ExtraConstants.LAUNCH_GAME, true));"
new = """mPlayButton.setOnClickListener(v -> { // CRYPTO_FEATURES
            Instance inst = Instances.loadSelectedInstance();
            if (inst != null) CryptoBootstrap.ensureMods(requireContext(), inst.getGameDirectory());
            ExtraCore.setValue(ExtraConstants.LAUNCH_GAME, true);
        });"""
if old in t: t = t.replace(old, new)
old2 = "mNewsButton.setOnClickListener(v -> Tools.openURL(requireActivity(), Tools.URL_HOME));"
new2 = """mNewsButton.setOnClickListener(v -> { // CRYPTO_FEATURES
            EditText input = new EditText(requireContext());
            input.setHint("host:port");
            new AlertDialog.Builder(requireContext()).setTitle("Quick Join").setView(input)
                .setPositiveButton("Save", (d,w)->{
                    Instance inst = Instances.loadSelectedInstance();
                    if (inst != null) {
                        CryptoBootstrap.writeQuickJoin(inst.getGameDirectory(), input.getText().toString());
                        Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Cancel", null).show();
        });"""
if old2 in t: t = t.replace(old2, new2)
old3 = "mDiscordButton.setOnClickListener(v -> Tools.openURL(requireActivity(), getString(R.string.social_media_invite)));"
new3 = """mDiscordButton.setOnClickListener(v -> { // CRYPTO_FEATURES
            String[] items = new String[]{"Low (1G)", "Balanced (2G)", "High (4G)"};
            new AlertDialog.Builder(requireContext()).setTitle("Performance")
                .setItems(items, (d,which)->{
                    String preset = which==0?"low":which==2?"high":"balanced";
                    CryptoBootstrap.applyPerformancePreset(requireContext(), preset);
                    Toast.makeText(requireContext(), preset, Toast.LENGTH_SHORT).show();
                }).show();
        });"""
if old3 in t: t = t.replace(old3, new3)
p.write_text(t)
print("menu features ok")
PY
fi
