package ru.cryptoclient.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import ru.cryptoclient.module.ModuleManager;
import ru.cryptoclient.ui.CryptoMenuScreen;

public class CryptoClient implements ClientModInitializer {
    public static CryptoClient INSTANCE;
    public static final String NAME = "Crypto Client";
    public ModuleManager modules;
    private KeyBinding menuKey;

    @Override
    public void onInitializeClient() {
        INSTANCE = this;
        modules = new ModuleManager();
        modules.init();
        menuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.crypto-client.menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.crypto-client"
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (menuKey.wasPressed()) {
                if (client.currentScreen == null) {
                    client.setScreen(new CryptoMenuScreen());
                }
            }
            modules.onTick(client);
        });
        HudRenderCallback.EVENT.register((context, tickCounter) -> {
            if (client() != null && client().player != null && !(client().currentScreen instanceof CryptoMenuScreen)) {
                modules.onHud(context, client());
            }
        });
    }

    private net.minecraft.client.MinecraftClient client() {
        return net.minecraft.client.MinecraftClient.getInstance();
    }
}
