package ru.cryptoclient.module.impl.hud;
import net.minecraft.client.MinecraftClient; import net.minecraft.client.gui.DrawContext;
import ru.cryptoclient.module.Category; import ru.cryptoclient.module.Module;
public class KeystrokesHud extends Module {
  public KeystrokesHud(){ super("Keystrokes","WASD overlay", Category.HUD);}
  public void onHud(DrawContext ctx, MinecraftClient mc){
    int x=mc.getWindow().getScaledWidth()-60, y=mc.getWindow().getScaledHeight()-60;
    boolean w=mc.options.forwardKey.isPressed(), a=mc.options.leftKey.isPressed();
    boolean s=mc.options.backKey.isPressed(), d=mc.options.rightKey.isPressed();
    boolean sp=mc.options.jumpKey.isPressed();
    drawKey(ctx,mc,"W",x+20,y,w); drawKey(ctx,mc,"A",x,y+20,a);
    drawKey(ctx,mc,"S",x+20,y+20,s); drawKey(ctx,mc,"D",x+40,y+20,d);
    drawKey(ctx,mc,"_",x+10,y+40,sp);
  }
  private void drawKey(DrawContext ctx, MinecraftClient mc, String t, int x, int y, boolean on){
    ctx.fill(x,y,x+18,y+18, on?0xAA3A6EA5:0x66000000);
    ctx.drawTextWithShadow(mc.textRenderer,t,x+5,y+5,0xFFFFFFFF);
  }
}
