package ru.cryptoclient.module.impl.hud;
import net.minecraft.client.MinecraftClient; import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import ru.cryptoclient.module.Category; import ru.cryptoclient.module.Module;
public class DurabilityHud extends Module {
  public DurabilityHud(){ super("Durability","Held item durability", Category.HUD);}
  public void onHud(DrawContext ctx, MinecraftClient mc){
    if(mc.player==null) return;
    ItemStack st=mc.player.getMainHandStack();
    if(st.isEmpty()||!st.isDamageable()) return;
    int left=st.getMaxDamage()-st.getDamage();
    ctx.drawTextWithShadow(mc.textRenderer, left+"/"+st.getMaxDamage(), 4, 64, 0xFFFFCC66);
  }
}
