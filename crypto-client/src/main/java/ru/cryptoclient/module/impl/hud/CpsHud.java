package ru.cryptoclient.module.impl.hud;
import net.minecraft.client.MinecraftClient; import net.minecraft.client.gui.DrawContext;
import ru.cryptoclient.module.Category; import ru.cryptoclient.module.Module;
import java.util.ArrayDeque;
public class CpsHud extends Module {
  private final ArrayDeque<Long> clicks=new ArrayDeque<>();
  public CpsHud(){ super("CPS","Clicks per second", Category.HUD);}
  public void onTick(MinecraftClient mc){
    if(mc.options.attackKey.isPressed()) clicks.add(System.currentTimeMillis());
    long now=System.currentTimeMillis();
    while(!clicks.isEmpty() && now-clicks.peekFirst()>1000) clicks.removeFirst();
  }
  public void onHud(DrawContext ctx, MinecraftClient mc){
    ctx.drawTextWithShadow(mc.textRenderer, clicks.size()+" CPS", 4, 40, 0xFF7FDBDA);
  }
}
