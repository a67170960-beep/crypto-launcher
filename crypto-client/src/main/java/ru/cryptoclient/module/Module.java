package ru.cryptoclient.module;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public abstract class Module {
    public final String name;
    public final String description;
    public final Category category;
    private boolean enabled;
    public Module(String name, String description, Category category) {
        this.name = name; this.description = description; this.category = category;
    }
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean v) {
        if (enabled == v) return;
        enabled = v;
        if (v) onEnable(); else onDisable();
    }
    public void toggle() { setEnabled(!enabled); }
    protected void onEnable() {}
    protected void onDisable() {}
    public void onTick(MinecraftClient mc) {}
    public void onHud(DrawContext ctx, MinecraftClient mc) {}
}
