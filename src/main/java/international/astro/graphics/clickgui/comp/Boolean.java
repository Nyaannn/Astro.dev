package international.astro.graphics.clickgui.comp;

import international.astro.Astro;
import international.astro.graphics.clickgui.OptionButton;
import international.astro.hack.Hack;
import international.astro.hack.option.Option;
import international.astro.hack.option.options.OBoolean;
import international.astro.util.RenderUtils;
import net.minecraft.client.gui.Gui;
import java.awt.*;

public class Boolean extends OptionButton {
    private final OBoolean option;

    public Boolean(Hack hack, Option option, int X, int Y, int W, int H) {
        super(hack, X, Y, W, H);
        this.option = (OBoolean) option;
    }

    @Override
    public void render(int mX, int mY) {
        Gui.drawRect(getX(), getY(), getX() + 105, getY() + 15, new Color(0, 0, 0,180).getRGB());
        if (option.isEnabled()) {
            Astro.clickGui.drawRect(getX() + 88, getY() + 2, getX() + 89, getY() + 15 - 2, Astro.colorManager.getRGBA());
            Astro.clickGui.drawRect(getX() + 99, getY() + 2, getX() + 100, getY() + 15 - 2, Astro.colorManager.getRGBA());
            Astro.clickGui.drawRect(getX() + 88, getY() + 1, getX() + 100, getY() + 2, Astro.colorManager.getRGBA());
            Astro.clickGui.drawRect(getX() + 88, getY() + 15 - 3, getX() + 100, getY() + 15 - 2, Astro.colorManager.getRGBA());
            Astro.clickGui.drawRect(getX() + 90, getY() + 3, getX() + 98, getY() + 15 - 4, Astro.colorManager.getRGBA());}
        else
        {
            Astro.clickGui.drawRect(getX() + 88, getY() + 2, getX() + 89, getY() + 15 - 2, Astro.colorManager.getRGBA());
            Astro.clickGui.drawRect(getX() + 99, getY() + 2, getX() + 100, getY() + 15 - 2, Astro.colorManager.getRGBA());
            Astro.clickGui.drawRect(getX() + 88, getY() + 1, getX() + 100, getY() + 2, Astro.colorManager.getRGBA());
            Astro.clickGui.drawRect(getX() + 88, getY() + 15 - 3, getX() + 100, getY() + 15 - 2, Astro.colorManager.getRGBA());
        }

        Astro.font.drawString(option.getName(), (float) (getX() + 3), (float) (getY() + 4), new Color(175, 175, 175, 255).getRGB());

        Gui.drawRect(getX(), getY(), getX() + 1, getY() + 15, Astro.colorManager.getRGBA());
        Gui.drawRect(getX() + 104, getY(), getX() + 105, getY() + 15, Astro.colorManager.getRGBA());
    }

    @Override
    public void mouseDown(int mX, int mY, int mB) {
        if (RenderUtils.isHovered(getX(), getY(), getW(), getH() - 1, mX, mY) && (mB == 0 || mB == 1)) {
            option.setEnabled(!option.isEnabled());
        }
    }
}
