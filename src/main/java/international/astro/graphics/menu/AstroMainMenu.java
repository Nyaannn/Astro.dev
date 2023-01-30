package international.astro.graphics.menu;

import international.astro.Astro;
import international.astro.hack.hacks.client.CustomFont;
import international.astro.util.RenderUtils;
import net.minecraft.client.gui.*;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class AstroMainMenu extends GuiScreen {

    ArrayList<Button> buttons = new ArrayList<>();
    private final Button singlePlayerButton;
    private final Button multiPlayerButton;
    private final Button settingsButton;
    private final Button discordButton;
    private final Button quitButton;

    private final Button vanillaMenu;

    public AstroMainMenu() {
        singlePlayerButton = new Button("Single", width / 2 - 75, height / 2,75);
        buttons.add(singlePlayerButton);

        multiPlayerButton = new Button("Multi", width / 2 - 75, height / 2 + 25,75);
        buttons.add(multiPlayerButton);

        settingsButton = new Button("Options", width / 2 - 125, height / 2 ,75);
        buttons.add(settingsButton);

        quitButton = new Button("Quit", width / 2 - 125, height / 2 + 25,75);
        buttons.add(quitButton);

        vanillaMenu = new Button("Vanilla Menu", width / 2 - 75, height / 2,75);
        buttons.add(vanillaMenu);

        //discord button
        discordButton = new Button("Discord", width / 2 - 75, height / 2,75);
        buttons.add(discordButton);
    }

    @Override
    public void drawScreen(int p_drawScreen_1_, int p_drawScreen_2_, float p_drawScreen_3_) {
        ScaledResolution sr = new ScaledResolution(mc);

        //logo n background
        RenderUtils.drawImage(0, 0, sr.getScaledWidth(), sr.getScaledHeight(), 0x40ffffff, "textures/background.png");
        RenderUtils.drawImage((sr.getScaledWidth() / 2)-115, sr.getScaledHeight() / 2 - 240, 240, 240, 0x40ffffff, "textures/logo.png");

        //creds n copyright's for mojang
        if(Astro.hackManager.getHack("CustomFont").isEnabled()) {
            Astro.MenuFont.drawString("Copyright Mojang Studios. Do not distribute!", sr.getScaledWidth() - Astro.MenuFont.getStringWidth("Copyright Mojang Studios. Do not distribute!") - 2, sr.getScaledHeight() - Astro.MenuFont.getFontHeight(), -1);
            Astro.MenuFont.drawString(Astro.NAME + " b" + Astro.VERSION + " By Logging4J, Nyaann and WMS :o)", 4, sr.getScaledHeight() - Astro.MenuFont.getFontHeight(), -1);
        }else{
            mc.fontRenderer.drawString("Copyright Mojang Studios. Do not distribute!", sr.getScaledWidth() - Astro.MenuFont.getStringWidth("Copyright Mojang Studios. Do not distribute!") - 2, sr.getScaledHeight() - Astro.MenuFont.getFontHeight(), -1);
            mc.fontRenderer.drawString(Astro.NAME + " b" + Astro.VERSION + " By Logging4J, Nyaann and WMS :o)", 4, sr.getScaledHeight() - Astro.MenuFont.getFontHeight(), -1);

        }

        //buttons
        settingsButton.renderButton(p_drawScreen_1_, p_drawScreen_2_, sr.getScaledWidth() / 2 + 5, sr.getScaledHeight() / 2);
        quitButton.renderButton(p_drawScreen_1_, p_drawScreen_2_, sr.getScaledWidth() / 2 + 5, sr.getScaledHeight() / 2 +25);
        singlePlayerButton.renderButton(p_drawScreen_1_, p_drawScreen_2_, sr.getScaledWidth() / 2 - 80, sr.getScaledHeight() / 2);
        multiPlayerButton.renderButton(p_drawScreen_1_, p_drawScreen_2_, sr.getScaledWidth() / 2 - 80, sr.getScaledHeight() / 2+25);
        vanillaMenu.renderButton(p_drawScreen_1_, p_drawScreen_2_, 10, 10);
        discordButton.renderButton(p_drawScreen_1_, p_drawScreen_2_, 90, 10);

        super.drawScreen(p_drawScreen_1_, p_drawScreen_2_, p_drawScreen_3_);
    }

    @Override
    protected void mouseClicked(int p_mouseClicked_1_, int p_mouseClicked_2_, int p_mouseClicked_3_) throws IOException {
        if (singlePlayerButton.isPressed(p_mouseClicked_1_, p_mouseClicked_2_)) {
            mc.displayGuiScreen(new GuiWorldSelection(this));
        } else if (multiPlayerButton.isPressed(p_mouseClicked_1_, p_mouseClicked_2_)) {
            mc.displayGuiScreen(new GuiMultiplayer(this));
        } else if (settingsButton.isPressed(p_mouseClicked_1_, p_mouseClicked_2_)) {
            mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
        } else if (quitButton.isPressed(p_mouseClicked_1_, p_mouseClicked_2_)) {
            mc.shutdown();
        } else if (vanillaMenu.isPressed(p_mouseClicked_1_, p_mouseClicked_2_)) {
            mc.displayGuiScreen(new GuiMainMenu());
        } else if (discordButton.isPressed(p_mouseClicked_1_, p_mouseClicked_2_)) {
            try {
                Desktop.getDesktop().browse(new URL("https://discord.gg/xADp2aVv79").toURI());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_2_, p_mouseClicked_3_);
    }

    public class Button {
        private final String s;
        private int x, y;
        private final int w, h;
        private final Color color, colorPressed;

        public Button(String s, int x, int y, int w) {
            this.s = s;
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = 20;
            this.color = new Color(0, 0, 0, 30);
            this.colorPressed = new Color(0, 0, 0, 50);
        }

        public void renderButton(int mouseX, int mouseY, int x, int y) {
            this.x = x;
            this.y = y;
            RenderUtils.roundedRect(x, y, w, h, 3, color);
            RenderUtils.roundedRect(x, y, w, h, 3, RenderUtils.isHovered(x, y, w, h, mouseX, mouseY) ? colorPressed : color );
            if(Astro.hackManager.getHack("CustomFont").isEnabled()) {
                Astro.MenuFont.drawString(s, x + w / 2 - Astro.MenuFont.getStringWidth(s) / 2, y + h / 2 - 4, -1);
            }else{
                mc.fontRenderer.drawString(s, x + w / 2 - Astro.MenuFont.getStringWidth(s) / 2, y + h / 2 - 4, -1);
            }

        }

        public boolean isPressed(int mouseX, int mouseY) {
            return RenderUtils.isHovered(getX(), getY(), getW(), getH(), mouseX, mouseY);
        }

        public int getW() {
            return w;
        }

        public int getH() {
            return h;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public String getString() {
            return s;
        }
    }
}
