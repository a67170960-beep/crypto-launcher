package ru.cryptoclient.module.impl.misc;
import net.minecraft.client.MinecraftClient; import net.minecraft.client.gui.DrawContext;
import ru.cryptoclient.module.Category; import ru.cryptoclient.module.Module;
public class Watermark extends Module {
  public Watermark(){ super("Watermark","Crypto Client watermark", Category.MISC); setEnabled(true);}
  public void onHud(DrawContext ctx, MinecraftClient mc){
    ctx.drawTextWithShadow(mc.textRenderer, "Crypto Client", 4, mc.getWindow().getScaledHeight()-12, 0xFF3A6EA5);
  }
}
