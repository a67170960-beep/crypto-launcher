package ru.cryptoclient.module.impl.hud;
import net.minecraft.client.MinecraftClient; import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.EquipmentSlot; import net.minecraft.item.ItemStack;
import ru.cryptoclient.module.Category; import ru.cryptoclient.module.Module;
public class ArmorHud extends Module {
  public ArmorHud(){ super("Armor HUD","Armor durability", Category.HUD); setEnabled(true);}
  public void onHud(DrawContext ctx, MinecraftClient mc){
    if(mc.player==null) return;
    int y=mc.getWindow().getScaledHeight()/2;
    EquipmentSlot[] slots={EquipmentSlot.HEAD,EquipmentSlot.CHEST,EquipmentSlot.LEGS,EquipmentSlot.FEET};
    for(EquipmentSlot s: slots){
      ItemStack st=mc.player.getEquippedStack(s);
      if(!st.isEmpty()){ ctx.drawItem(st, 4, y); y+=18; }
    }
  }
}
