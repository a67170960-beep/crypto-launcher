package ru.cryptoclient.module.impl.performance;
import net.minecraft.client.MinecraftClient; import net.minecraft.client.gui.DrawContext;
import ru.cryptoclient.module.Category; import ru.cryptoclient.module.Module;
public class MemoryHud extends Module {
  public MemoryHud(){ super("Memory","JVM memory usage", Category.PERFORMANCE);}
  public void onHud(DrawContext ctx, MinecraftClient mc){
    Runtime r=Runtime.getRuntime();
    long used=(r.totalMemory()-r.freeMemory())/1024/1024;
    long max=r.maxMemory()/1024/1024;
    ctx.drawTextWithShadow(mc.textRenderer, used+"/"+max+" MB", 4, 88, 0xFF8FBC8F);
  }
}
