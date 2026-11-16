package ru.cryptoclient.module.impl.visual;
import net.minecraft.client.MinecraftClient;
import ru.cryptoclient.module.Category; import ru.cryptoclient.module.Module;
public class Fullbright extends Module {
  private double old=1;
  public Fullbright(){ super("Fullbright","Max gamma", Category.VISUAL);}
  protected void onEnable(){ var o=MinecraftClient.getInstance().options; old=o.getGamma().getValue(); o.getGamma().setValue(16.0);}
  protected void onDisable(){ MinecraftClient.getInstance().options.getGamma().setValue(old);}
}
