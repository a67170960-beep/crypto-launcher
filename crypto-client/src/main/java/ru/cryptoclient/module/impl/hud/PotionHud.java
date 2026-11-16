package ru.cryptoclient.module.impl.hud;
import net.minecraft.client.MinecraftClient; import net.minecraft.client.gui.DrawContext;
import ru.cryptoclient.module.Category; import ru.cryptoclient.module.Module;
public class PotionHud extends Module {
  public PotionHud(){ super("Potion HUD","Active effects list", Category.HUD);}
  public void onHud(DrawContext ctx, MinecraftClient mc){
    if(mc.player==null) return;
    int y=50;
    for(var e: mc.player.getStatusEffects()){
      String n=e.getEffectType().value().getName().getString();
      int sec=e.getDuration()/20;
      ctx.drawTextWithShadow(mc.textRenderer, n+" "+sec+"s", 4, y, 0xFFC8A0FF);
      y+=12;
    }
  }
}
