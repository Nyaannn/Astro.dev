package international.astro.graphics.clickgui.comp;

import international.astro.Astro;
import international.astro.graphics.clickgui.OptionButton;
import international.astro.hack.Hack;
import international.astro.hack.option.Option;
import international.astro.hack.option.options.OList;
import international.astro.util.RenderUtils;
import net.minecraft.client.gui.Gui;

import java.awt.*;

public class Mode extends OptionButton {
    private final OList option;

    public Mode(Hack hack, Option option, int X, int Y, int W, int H) {
        super(hack, X, Y, W, H);
        this.option = (OList) option;
    }


    @Override
    public void render(int mX, int mY) {
        Astro.clickGui.drawRect(getX(), getY(), getX() + 105, getY() + 15, new Color(0, 0, 0, 180).getRGB());
        Astro.font.drawString(option.getName(), (float) (getX() + 3), (float) (getY() + 4), new Color(255, 255, 255, 255).getRGB());
        Astro.font.drawString(option.getMode(), (float) ((getX() + getW()) - mc.fontRenderer.getStringWidth(option.getMode())), (float) (getY() + 4), new Color(255, 255, 255, 255).getRGB());
        Gui.drawRect(getX(), getY(), getX() + 1, getY() + 15, Astro.colorManager.getRGBA());
        Gui.drawRect(getX() + 104, getY(), getX() + 105, getY() + 15, Astro.colorManager.getRGBA());
    }

    @Override
    public void mouseDown(int mX, int mY, int mB) {
        if (RenderUtils.isHovered(getX(), getY(), getW(), getH() - 1, mX, mY)) {
            if (mB == 0) {
                int i = 0;
                int listIndex = 0;
                for (String listName : option.getModes()) {
                    if (listName.equals(option.getMode())) listIndex = i;
                    i++;
                }
                if (listIndex == option.getModes().size() - 1) {
                    option.setMode(option.getModes().get(0));
                }
                else {
                    listIndex++;
                    i = 0;
                    for (String listName : option.getModes()) {
                        if (i == listIndex) option.setMode(listName);
                        i++;
                    }
                }
            }
            else if (mB == 1) {
                int i = 0;
                int listIndex = 0;
                for (String listName : option.getModes()) {
                    if (listName.equals(option.getMode())) listIndex = i;
                    i++;
                }
                if (listIndex == 0) {
                    option.setMode(option.getModes().get(option.getModes().size() - 1));
                }
                else {
                    listIndex--;
                    i = 0;
                    for (String listName : option.getModes()) {
                        if (i == listIndex) option.setMode(listName);
                        i++;
                    }
                }
            }
        }
    }
}
