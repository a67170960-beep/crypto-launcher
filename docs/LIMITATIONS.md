# Limitations (honest)

Running **Minecraft Java Edition** on Android needs:

1. OpenJDK/JRE for ARM
2. LWJGL + OpenGL ES bridge
3. Input (touch → mouse/keyboard)
4. Version/library downloader (Mojang manifests)
5. Microsoft authentication

Projects like **PojavLauncher** (GPL) implement this over years.

Crypto Launcher **alpha** ships:
- Working Fabric mod (Crypto Client)
- Android shell for profiles/servers/performance

Play button does **not** claim a fake JVM. Runtime integration is a separate major milestone under compatible open-source licenses.
