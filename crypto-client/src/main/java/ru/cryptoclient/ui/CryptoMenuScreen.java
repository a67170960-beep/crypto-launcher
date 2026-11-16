package ru.cryptoclient.ui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import ru.cryptoclient.client.CryptoClient;
import ru.cryptoclient.module.Category;
import ru.cryptoclient.module.Module;

import java.util.List;

public class CryptoMenuScreen extends Screen {
    private Category selected = Category.HUD;
    private int scroll;

    public CryptoMenuScreen() {
        super(Text.literal("Crypto Client"));
    }

    @Override
    protected void init() {
        int x = 12, y = 40;
        for (Category c : Category.values()) {
            Category cat = c;
            addDrawableChild(ButtonWidget.builder(Text.literal(c.title), b -> selected = cat)
                    .dimensions(x, y, 90, 18).build());
            y += 22;
        }
        addDrawableChild(ButtonWidget.builder(Text.literal("Close"), b -> close())
                .dimensions(width - 70, height - 28, 60, 18).build());
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        ctx.fill(0, 0, width, height, 0xCC0A0E16);
        ctx.fill(0, 0, width, 28, 0xFF0D1520);
        ctx.drawTextWithShadow(textRenderer, "Crypto Client", 12, 10, 0xFF6FA8FF);
        ctx.drawTextWithShadow(textRenderer, "Right Shift · Legit only", 120, 10, 0xFF888888);

        int panelX = 120, panelY = 40, panelW = width - 140, panelH = height - 80;
        ctx.fill(panelX, panelY, panelX + panelW, panelY + panelH, 0xFF121A24);
        ctx.fill(panelX, panelY, panelX + panelW, panelY + 1, 0xFF3A6EA5);

        List<Module> list = CryptoClient.INSTANCE.modules.byCategory(selected);
        int y = panelY + 10 - scroll;
        for (Module m : list) {
            if (y > panelY && y < panelY + panelH - 16) {
                int color = m.isEnabled() ? 0xFF6FA8FF : 0xFFAAAAAA;
                ctx.drawTextWithShadow(textRenderer, (m.isEnabled() ? "[ON] " : "[OFF] ") + m.name, panelX + 10, y, color);
                ctx.drawTextWithShadow(textRenderer, m.description, panelX + 10, y + 10, 0xFF666666);
            }
            y += 28;
        }
        super.render(ctx, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int panelX = 120, panelY = 40, panelW = width - 140, panelH = height - 80;
        if (mouseX >= panelX && mouseX <= panelX + panelW && mouseY >= panelY && mouseY <= panelY + panelH) {
            List<Module> list = CryptoClient.INSTANCE.modules.byCategory(selected);
            int y = panelY + 10 - scroll;
            for (Module m : list) {
                if (mouseY >= y && mouseY <= y + 24) {
                    m.toggle();
                    return true;
                }
                y += 28;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean shouldPause() { return false; }
}
