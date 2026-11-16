package ru.cryptoclient.module.impl.utility;
import net.minecraft.client.MinecraftClient;
import ru.cryptoclient.module.Category; import ru.cryptoclient.module.Module;
public class CoordsCopy extends Module {
  public CoordsCopy(){ super("CopyCoords","Copies XYZ to clipboard when enabled once", Category.UTILITY);}
  protected void onEnable(){
    var mc=MinecraftClient.getInstance();
    if(mc.player!=null){
      String s=String.format("%.1f %.1f %.1f", mc.player.getX(), mc.player.getY(), mc.player.getZ());
      mc.keyboard.setClipboard(s);
    }
    setEnabled(false);
  }
}
