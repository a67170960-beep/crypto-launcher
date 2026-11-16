package ru.cryptoclient.module.impl.pvp;
import net.minecraft.client.MinecraftClient; import net.minecraft.client.gui.DrawContext;
import ru.cryptoclient.module.Category; import ru.cryptoclient.module.Module;
public class ComboCounter extends Module {
  private int combo; private long last;
  public ComboCounter(){ super("Combo","Hit combo counter", Category.PVP);}
  public void onTick(MinecraftClient mc){
    if(mc.player==null) return;
    if(System.currentTimeMillis()-last>1500) combo=0;
    if(mc.options.attackKey.wasPressed()){ combo++; last=System.currentTimeMillis(); }
  }
  public void onHud(DrawContext ctx, MinecraftClient mc){
    if(combo>0) ctx.drawTextWithShadow(mc.textRenderer, combo+" combo", mc.getWindow().getScaledWidth()/2-15, mc.getWindow().getScaledHeight()/2+20, 0xFFFFAA00);
  }
}
