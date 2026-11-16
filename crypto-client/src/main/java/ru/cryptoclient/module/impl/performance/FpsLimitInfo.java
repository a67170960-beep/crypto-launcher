package ru.cryptoclient.module.impl.performance;
import net.minecraft.client.MinecraftClient; import net.minecraft.client.gui.DrawContext;
import ru.cryptoclient.module.Category; import ru.cryptoclient.module.Module;
public class FpsLimitInfo extends Module {
  public FpsLimitInfo(){ super("FPS Limit Info","Shows current FPS limit", Category.PERFORMANCE);}
  public void onHud(DrawContext ctx, MinecraftClient mc){
    int lim=mc.options.getMaxFps().getValue();
    ctx.drawTextWithShadow(mc.textRenderer, "Limit "+lim, 4, 76, 0xFF888888);
  }
}
