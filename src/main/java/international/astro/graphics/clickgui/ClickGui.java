package international.astro.graphics.clickgui;


import international.astro.Astro;
import international.astro.hack.Hack;
import international.astro.hack.hacks.client.ClickGuiMod;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;

public class ClickGui extends GuiScreen {

    private final ArrayList<Box> windows = new ArrayList<>();

    public ClickGui(){
        int xOffset = 5;
        for (Hack.Category category : Hack.Category.values()) {
            Box window = new Box(category, xOffset, 25, 105, 15);
            windows.add(window);
            xOffset += 110;
        }
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        doScroll();
        for (Box window : windows) {
            window.render(mouseX, mouseY);
        }
        if (OpenGlHelper.shadersSupported && mc.getRenderViewEntity() instanceof EntityPlayer && ClickGuiMod.blur.isEnabled()) {
            if (mc.entityRenderer.getShaderGroup() != null) {
                    mc.entityRenderer.getShaderGroup().deleteShaderGroup();
            }
            mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        for (Box window : windows) {
            window.mouseDown(mouseX, mouseY, mouseButton);
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        for (Box window : windows) {
            window.mouseUp(mouseX, mouseY);
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        for (Box window : windows) {
            window.keyPress(keyCode);
        }
        if (keyCode == Keyboard.KEY_ESCAPE) {
            mc.displayGuiScreen(null);
            if (mc.currentScreen == null) {
                mc.setIngameFocus();
            }
        }
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }


    @Override
    public void onGuiClosed() {
        for (Box window : windows) {
            window.close();
        }
        Astro.hackManager.getHack("ClickGUI").toggle();
        if (mc.entityRenderer.getShaderGroup() != null) {
            mc.entityRenderer.getShaderGroup().deleteShaderGroup();
        }
    }

    public void drawGradient(int left, int top, int right, int bottom, int startColor, int endColor) {
        drawGradientRect(left, top, right, bottom, startColor, endColor);
    }

    private void doScroll() {
        int w = Mouse.getDWheel();
        if (w < 0) {
            for (Box window : windows) {
                window.setY(window.getY() - 8);
            }
        }
        else if (w > 0) {
            for (Box window : windows) {
                window.setY(window.getY() + 8);
            }
        }
    }
}
