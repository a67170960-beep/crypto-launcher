package ru.cryptoclient.module.impl.misc;
import net.minecraft.client.MinecraftClient; import net.minecraft.client.gui.DrawContext;
import ru.cryptoclient.client.CryptoClient;
import ru.cryptoclient.module.Category; import ru.cryptoclient.module.Module;
public class ArrayListHud extends Module {
  public ArrayListHud(){ super("ArrayList","Enabled modules list", Category.MISC); setEnabled(true);}
  public void onHud(DrawContext ctx, MinecraftClient mc){
    int y=4, x=mc.getWindow().getScaledWidth()-8;
    for(Module m: CryptoClient.INSTANCE.modules.modules){
      if(!m.isEnabled()||m==this) continue;
      int w=mc.textRenderer.getWidth(m.name);
      ctx.drawTextWithShadow(mc.textRenderer, m.name, x-w, y, 0xFF6FA8FF);
      y+=12;
    }
  }
}
