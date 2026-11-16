package ru.cryptoclient.module.impl.hud;
import net.minecraft.client.MinecraftClient; import net.minecraft.client.gui.DrawContext;
import ru.cryptoclient.module.Category; import ru.cryptoclient.module.Module;
public class SessionTimerHud extends Module {
  private long start=System.currentTimeMillis();
  public SessionTimerHud(){ super("Session","Session playtime", Category.HUD);}
  public void onHud(DrawContext ctx, MinecraftClient mc){
    long s=(System.currentTimeMillis()-start)/1000;
    String t=String.format("%02d:%02d:%02d", s/3600, (s%3600)/60, s%60);
    ctx.drawTextWithShadow(mc.textRenderer, t, 4, 52, 0xFFAAAAAA);
  }
}
