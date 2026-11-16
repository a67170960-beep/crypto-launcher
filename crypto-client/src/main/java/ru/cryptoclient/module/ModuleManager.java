package ru.cryptoclient.module;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import ru.cryptoclient.module.impl.hud.*;
import ru.cryptoclient.module.impl.performance.*;
import ru.cryptoclient.module.impl.pvp.*;
import ru.cryptoclient.module.impl.utility.*;
import ru.cryptoclient.module.impl.visual.*;
import ru.cryptoclient.module.impl.misc.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    public final List<Module> modules = new ArrayList<>();

    public void init() {
        // HUD
        modules.add(new FpsHud());
        modules.add(new CoordsHud());
        modules.add(new DirectionHud());
        modules.add(new ArmorHud());
        modules.add(new PotionHud());
        modules.add(new KeystrokesHud());
        modules.add(new CpsHud());
        modules.add(new PingHud());
        modules.add(new ClockHud());
        modules.add(new SessionTimerHud());
        modules.add(new DurabilityHud());
        // Visual
        modules.add(new CrosshairCustom());
        modules.add(new Fullbright());
        modules.add(new NoHurtCam());
        modules.add(new ScoreboardToggle());
        // Performance
        modules.add(new FpsLimitInfo());
        modules.add(new ParticleHint());
        modules.add(new MemoryHud());
        // Utility
        modules.add(new CoordsCopy());
        modules.add(new SprintToggle());
        modules.add(new ZoomHint());
        // PvP legit
        modules.add(new AttackCooldownHud());
        modules.add(new ComboCounter());
        modules.add(new TargetHud());
        // Misc
        modules.add(new ArrayListHud());
        modules.add(new Watermark());
    }

    public List<Module> byCategory(Category c) {
        return modules.stream().filter(m -> m.category == c).collect(Collectors.toList());
    }

    public void onTick(MinecraftClient mc) {
        for (Module m : modules) if (m.isEnabled()) m.onTick(mc);
    }

    public void onHud(DrawContext ctx, MinecraftClient mc) {
        for (Module m : modules) if (m.isEnabled()) m.onHud(ctx, mc);
    }
}
