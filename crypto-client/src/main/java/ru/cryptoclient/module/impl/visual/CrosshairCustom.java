package ru.cryptoclient.module.impl.visual;
import net.minecraft.client.MinecraftClient; import net.minecraft.client.gui.DrawContext;
import ru.cryptoclient.module.Category; import ru.cryptoclient.module.Module;
public class CrosshairCustom extends Module {
  public CrosshairCustom(){ super("Crosshair","Custom crosshair", Category.VISUAL);}
  public void onHud(DrawContext ctx, MinecraftClient mc){
    int cx=mc.getWindow().getScaledWidth()/2, cy=mc.getWindow().getScaledHeight()/2;
    ctx.fill(cx-4, cy, cx+5, cy+1, 0xFF6FA8FF);
    ctx.fill(cx, cy-4, cx+1, cy+5, 0xFF6FA8FF);
  }
}
