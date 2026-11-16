package ru.cryptoclient.module.impl.pvp;
import net.minecraft.client.MinecraftClient; import net.minecraft.client.gui.DrawContext;
import ru.cryptoclient.module.Category; import ru.cryptoclient.module.Module;
public class AttackCooldownHud extends Module {
  public AttackCooldownHud(){ super("Attack CD","Attack cooldown bar", Category.PVP); setEnabled(true);}
  public void onHud(DrawContext ctx, MinecraftClient mc){
    if(mc.player==null) return;
    float cd=mc.player.getAttackCooldownProgress(0f);
    int w=mc.getWindow().getScaledWidth(), h=mc.getWindow().getScaledHeight();
    int bw=40; int x=w/2-bw/2, y=h/2+12;
    ctx.fill(x,y,x+bw,y+3,0x88000000);
    ctx.fill(x,y,x+(int)(bw*cd),y+3, cd>=1f?0xFF6FA8FF:0xFFFF6666);
  }
}
