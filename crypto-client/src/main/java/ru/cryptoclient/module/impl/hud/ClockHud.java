package ru.cryptoclient.module.impl.hud;
import net.minecraft.client.MinecraftClient; import net.minecraft.client.gui.DrawContext;
import ru.cryptoclient.module.Category; import ru.cryptoclient.module.Module;
import java.time.LocalTime; import java.time.format.DateTimeFormatter;
public class ClockHud extends Module {
  public ClockHud(){ super("Clock","Real time clock", Category.HUD);}
  public void onHud(DrawContext ctx, MinecraftClient mc){
    String t=LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    ctx.drawTextWithShadow(mc.textRenderer, t, mc.getWindow().getScaledWidth()-60, 16, 0xFFCCCCCC);
  }
}
