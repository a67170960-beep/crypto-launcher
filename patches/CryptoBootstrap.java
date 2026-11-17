package git.artdeell.mojo.crypto;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public final class CryptoBootstrap {
    private static final String TAG = "CryptoBootstrap";
    private static final String ASSET_DIR = "crypto-mods";
    private CryptoBootstrap() {}

    public static void ensureMods(Context context, File gameDirectory) {
        if (context == null || gameDirectory == null) return;
        File mods = new File(gameDirectory, "mods");
        if (!mods.exists() && !mods.mkdirs()) return;
        try {
            String[] names = context.getAssets().list(ASSET_DIR);
            if (names == null) return;
            for (String name : names) {
                if (name == null || !name.endsWith(".jar")) continue;
                File out = new File(mods, name);
                boolean isClient = name.toLowerCase().contains("crypto");
                if (out.exists() && !isClient) continue;
                try (InputStream in = context.getAssets().open(ASSET_DIR + "/" + name);
                     OutputStream os = new FileOutputStream(out)) {
                    byte[] buf = new byte[8192];
                    int n;
                    while ((n = in.read(buf)) != -1) os.write(buf, 0, n);
                }
                Log.i(TAG, "Installed: " + out);
            }
        } catch (Exception e) {
            Log.e(TAG, "ensureMods", e);
        }
    }

    public static void writeQuickJoin(File gameDirectory, String hostPort) {
        if (gameDirectory == null || hostPort == null || hostPort.trim().isEmpty()) return;
        try (FileOutputStream os = new FileOutputStream(new File(gameDirectory, "crypto_quickjoin.txt"))) {
            os.write(hostPort.trim().getBytes("UTF-8"));
        } catch (Exception e) {
            Log.e(TAG, "quickjoin", e);
        }
    }

    public static void applyPerformancePreset(Context context, String preset) {
        SharedPreferences crypto = context.getSharedPreferences("crypto_launcher", Context.MODE_PRIVATE);
        int ramMb = "low".equals(preset) ? 1024 : "high".equals(preset) ? 4096 : 2048;
        crypto.edit().putString("perf_preset", preset).putInt("ram_mb", ramMb).apply();
        Log.i(TAG, "preset=" + preset + " ram=" + ramMb);
    }
}
