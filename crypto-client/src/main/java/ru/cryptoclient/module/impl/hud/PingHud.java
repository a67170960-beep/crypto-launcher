package ru.cryptoclient.module.impl.hud;
import net.minecraft.client.MinecraftClient; import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.PlayerListEntry;
import ru.cryptoclient.module.Category; import ru.cryptoclient.module.Module;
public class PingHud extends Module {
  public PingHud(){ super("Ping","Latency display", Category.HUD);}
  public void onHud(DrawContext ctx, MinecraftClient mc){
    if(mc.getNetworkHandler()==null||mc.player==null) return;
    PlayerListEntry e=mc.getNetworkHandler().getPlayerListEntry(mc.player.getUuid());
    int ping=e==null?0:e.getLatency();
    ctx.drawTextWithShadow(mc.textRenderer, ping+" ms", mc.getWindow().getScaledWidth()-50, 4, 0xFF90EE90);
  }
}
