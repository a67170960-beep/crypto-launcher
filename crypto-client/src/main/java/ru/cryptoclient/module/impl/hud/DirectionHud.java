package ru.cryptoclient.module.impl.hud;
import net.minecraft.client.MinecraftClient; import net.minecraft.client.gui.DrawContext;
import ru.cryptoclient.module.Category; import ru.cryptoclient.module.Module;
public class DirectionHud extends Module {
  public DirectionHud(){ super("Direction","Facing direction", Category.HUD);}
  public void onHud(DrawContext ctx, MinecraftClient mc){
    if(mc.player==null) return;
    float yaw=mc.player.getYaw();
    String[] d={"S","SW","W","NW","N","NE","E","SE"};
    int i=((int)Math.floor((yaw+180+22.5)/45))&7;
    ctx.drawTextWithShadow(mc.textRenderer, d[i]+" "+String.format("%.0f", yaw), 4, 28, 0xFFB0B0B0);
  }
}
