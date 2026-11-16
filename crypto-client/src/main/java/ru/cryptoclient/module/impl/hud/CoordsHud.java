package ru.cryptoclient.module.impl.hud;
import net.minecraft.client.MinecraftClient; import net.minecraft.client.gui.DrawContext;
import ru.cryptoclient.module.Category; import ru.cryptoclient.module.Module;
public class CoordsHud extends Module {
  public CoordsHud(){ super("Coordinates","XYZ display", Category.HUD); setEnabled(true);}
  public void onHud(DrawContext ctx, MinecraftClient mc){
    if(mc.player==null) return;
    var p=mc.player;
    String s=String.format("XYZ %.1f %.1f %.1f", p.getX(), p.getY(), p.getZ());
    ctx.drawTextWithShadow(mc.textRenderer, s, 4, 16, 0xFFE0E0E0);
  }
}
