package ru.cryptoclient.module.impl.utility;
import net.minecraft.client.MinecraftClient;
import ru.cryptoclient.module.Category; import ru.cryptoclient.module.Module;
public class SprintToggle extends Module {
  public SprintToggle(){ super("ToggleSprint","Hold sprint", Category.UTILITY);}
  public void onTick(MinecraftClient mc){
    if(mc.player!=null && mc.player.forwardSpeed>0) mc.player.setSprinting(true);
  }
}
