package international.astro.graphics.clickgui.comp;

import international.astro.Astro;
import international.astro.graphics.clickgui.OptionButton;
import international.astro.hack.Hack;
import international.astro.util.RenderUtils;
import net.minecraft.client.gui.Gui;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class Bind extends OptionButton {
    private final Hack hack;
    private boolean binding;

    public Bind(Hack hack, int x, int y, int w, int h) {
        super(hack, x, y, w, h);
        this.hack = hack;
    }

    public void render(int mX, int mY) {
        Gui.drawRect(getX(), getY(), getX() + 105, getY() + 15, new Color(0, 0, 0,180).getRGB());
        Astro.font.drawString("Bind", (float) (getX() + 3), (float) (getY() + 4), new Color(255, 255, 255, 255).getRGB());
        if (binding) {
            Astro.font.drawString("...", (float) ((getX() + getW() - 6) - mc.fontRenderer.getStringWidth("...")), (float) (getY() + 4), new Color(255, 255, 255, 255).getRGB());
        }
        else {
            try {
                Astro.font.drawString(Keyboard.getKeyName(hack.getBind()), (float) ((getX() + getW() - 3) - mc.fontRenderer.getStringWidth(Keyboard.getKeyName(hack.getBind()))), (float) (getY() + 4), new Color(255, 255, 255, 255).getRGB());
            }
            catch (Exception e) {
                Astro.font.drawString("NONE", (float) ((getX() + getW()) - mc.fontRenderer.getStringWidth("NONE")), (float) (getY() + 4), new Color(255, 255, 255, 255).getRGB());
            }
        }
        Gui.drawRect(getX(), getY(), getX() + 1, getY() + 15, Astro.colorManager.getRGBA());
        Gui.drawRect(getX() + 104, getY(), getX() + 105, getY() + 15, Astro.colorManager.getRGBA());
        Gui.drawRect(getX(), getY() + 15, getX() + 105, getY() + 16, Astro.colorManager.getRGBA());
    }

    public void mouseDown(int mX, int mY, int mB) {
        if (RenderUtils.isHovered(getX(), getY(), getW(), getH() - 1, mX, mY)) {
            binding = !binding;
        }
    }

    public void keyPress(int key) {
        if (binding) {
            if (key == Keyboard.KEY_DELETE || key == Keyboard.KEY_ESCAPE || key == Keyboard.KEY_BACK || key == Keyboard.KEY_NONE) {
                getHack().setBind(Keyboard.KEYBOARD_SIZE);
            }
            else {
                getHack().setBind(key);
            }
            binding = false;
        }
    }
}
