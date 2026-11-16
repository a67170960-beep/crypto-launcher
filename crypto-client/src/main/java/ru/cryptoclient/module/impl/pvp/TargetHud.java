package ru.cryptoclient.module.impl.pvp;
import net.minecraft.client.MinecraftClient; import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity; import net.minecraft.util.hit.EntityHitResult;
import ru.cryptoclient.module.Category; import ru.cryptoclient.module.Module;
public class TargetHud extends Module {
  public TargetHud(){ super("Target HUD","Looked-at player info", Category.PVP);}
  public void onHud(DrawContext ctx, MinecraftClient mc){
    if(!(mc.crosshairTarget instanceof EntityHitResult ehr)) return;
    if(!(ehr.getEntity() instanceof PlayerEntity p)) return;
    String s=p.getName().getString()+" "+String.format("%.0f", p.getHealth())+" HP";
    ctx.drawTextWithShadow(mc.textRenderer, s, mc.getWindow().getScaledWidth()/2-40, mc.getWindow().getScaledHeight()/2+32, 0xFFFF8888);
  }
}
