package international.astro.graphics.clickgui;

import international.astro.Astro;
import international.astro.graphics.clickgui.comp.Bind;
import international.astro.graphics.clickgui.comp.Boolean;
import international.astro.graphics.clickgui.comp.Mode;
import international.astro.graphics.clickgui.comp.Slider;
import international.astro.hack.Hack;
import international.astro.hack.option.Option;
import international.astro.hack.option.options.OBoolean;
import international.astro.hack.option.options.ODouble;
import international.astro.hack.option.options.OList;
import international.astro.util.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import java.awt.*;
import java.util.ArrayList;

public class HackButton {
    private final Minecraft mc = Minecraft.getMinecraft();
    private final Hack hack;
    private final ArrayList<OptionButton> buttons = new ArrayList<>();
    private final int W;
    private final int H;
    private int X;
    private int Y;
    private boolean open;
    private int showingModuleCount;
    private boolean opening;
    private boolean closing;

    public HackButton(Hack hack, int x, int y, int w, int h) {
        this.hack = hack;
        X = x;
        Y = y;
        W = w;
        H = h;
        int n = 0;
        for (Option option : hack.getOptions()) {
            OptionButton optionButton = null;
            if(option instanceof OBoolean) {
                optionButton = new Boolean(hack,option, X, Y + H + n, W, H);
            }
            else if (option instanceof ODouble) {
                optionButton = new Slider(hack, option, X, Y + H + n, W, H);
            }
            else if (option instanceof OList) {
                optionButton = new Mode(hack, option, X, Y + H + n, W, H);
            }
            if (optionButton != null) {
                buttons.add(optionButton);
                n += H;
            }
        }
        buttons.add(new Bind(hack, X, Y + H + n, W, H));
    }

    public void render(int mX, int mY) {
        Gui.drawRect(X, Y, X + W, Y + H, new Color(0, 0, 0, 180).getRGB());
        if (hack.isEnabled()) {
            Gui.drawRect(X, Y, X + W, Y + H, Astro.colorManager.getRGBA());
           Astro.font.drawString(hack.getName(), (float) (X + 3), (float) (Y + 4), new Color(255, 255, 255).getRGB());
        } else {
            Astro.font.drawString(hack.getName(), (float) (X + 3), (float) (Y + 4), new Color(255, 255, 255, 255).getRGB());
        }
        Gui.drawRect(X, Y - 1, X + 1, Y + H + 1, new Color(0, 0, 0, 180).getRGB());
        Gui.drawRect(X + 104, Y - 1, X + W, Y + H, new Color(0, 0, 0, 180).getRGB());
        if (opening) {
            showingModuleCount++;
            if (showingModuleCount == buttons.size()) {
                opening = false;
                open = true;
            }
        }
        
        if (closing) {
            showingModuleCount--;
            if (showingModuleCount == 0) {
                closing = false;
                open = false;
            }
        }

        Gui.drawRect(X, Y + 15, X + W, Y + 16, Astro.colorManager.getRGBA());
    }

    public void mouseDown(int mX, int mY, int mB) {
        if (RenderUtils.isHovered(X, Y, W, H - 1, mX, mY)) {
            if (mB == 0) {
                hack.toggle();
                if (hack.getName().equals("ClickGUI")) {
                    mc.displayGuiScreen(null);
                }
            }
            else if (mB == 1) {
                processRightClick();
            }
        }
        if (open) {
            for (OptionButton optionButton : buttons) {
                optionButton.mouseDown(mX, mY, mB);
            }
        }
    }

    public void mouseUp(int mX, int mY) {
        for (OptionButton optionButton : buttons) {
            optionButton.mouseUp(mX, mY);
        }
    }

    public void keyPress(int key) {
        for (OptionButton optionButton : buttons) {
            optionButton.keyPress(key);
        }
    }

    public void close() {
        for (OptionButton button : buttons) {
            button.close();
        }
    }

    public void setX(int x)
    {
        X = x;
    }

    public void setY(int y)
    {
        Y = y;
    }

    public boolean isOpen()
    {
        return open;
    }

    public Hack getModule()
    {
        return hack;
    }

    public ArrayList<OptionButton> getButtons()
    {
        return buttons;
    }

    public int getShowingModuleCount()
    {
        return showingModuleCount;
    }

    public boolean isOpening()
    {
        return opening;
    }

    public boolean isClosing()
    {
        return closing;
    }

    public void processRightClick() {
        if (!open) {
            showingModuleCount = 0;
            opening = true;
        }
        else {
            showingModuleCount = buttons.size();
            closing = true;
        }
    }
}
