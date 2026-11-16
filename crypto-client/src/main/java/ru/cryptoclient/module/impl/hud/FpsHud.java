package ru.cryptoclient.module.impl.hud;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import ru.cryptoclient.module.Category; import ru.cryptoclient.module.Module;
public class FpsHud extends Module {
  public FpsHud(){ super("FPS","FPS counter", Category.HUD); setEnabled(true);}
  public void onHud(DrawContext ctx, MinecraftClient mc){
    ctx.drawTextWithShadow(mc.textRenderer, mc.getCurrentFps()+" FPS", 4, 4, 0xFF6FA8FF);
  }
}
