package international.astro.graphics.clickgui;

import international.astro.Astro;
import international.astro.hack.Hack;
import international.astro.util.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;
import java.util.ArrayList;

public class Box {
    protected Minecraft mc = Minecraft.getMinecraft();

    ScaledResolution scaledRes = new ScaledResolution(mc);
    private final ArrayList<HackButton> buttons = new ArrayList<HackButton>();
    private final Hack.Category category;
    private final int W;
    private final int H;
    private final ArrayList<HackButton> buttonsBeforeClosing = new ArrayList<>();
    private int X;
    private int Y;
    private int dragX;
    private int dragY;
    private boolean open = true;
    private boolean dragging;
    private int showingButtonCount;
    private boolean opening;
    private boolean closing;

    public Box(Hack.Category category, int x, int y, int w, int h) {
        this.category = category;
        X = x;
        Y = y;
        W = w;
        H = h;

        int yOffset = Y + H;

        for (Hack hack : Astro.hackManager.getHacksInCategory(category)) {
            HackButton button = new HackButton(hack, X, yOffset, W, H);
            buttons.add(button);
            yOffset += H;
        }
        showingButtonCount = buttons.size();
    }

    public void render(int mX, int mY) {
        if (dragging) {
            X = dragX + mX;
            Y = dragY + mY;
        }
        Gui.drawRect(X, Y + 1, X + W, Y + H, Astro.colorManager.getRGBA());
        Gui.drawRect(X, Y + 1, X + 1, Y + H, Astro.colorManager.getRGBA());
        Gui.drawRect(X + 104, Y + 1, X + W, Y + H, Astro.colorManager.getRGBA());
        Gui.drawRect(X, Y + 1, X + W, Y + 2, Astro.colorManager.getRGBA());
        Gui.drawRect(X, Y + 14, X + W, Y + 15, Astro.colorManager.getRGBA());
        Astro.font.drawString(category.getName(), X + ( W / 2 ) - (mc.fontRenderer.getStringWidth(category.getName()) / 2), (float) (Y + 2.5), new Color(255, 255, 255, 255).getRGB());
        if (open || opening || closing) {
            int modY = Y + H;
            int moduleRenderCount = 0;
            for (HackButton moduleButton : buttons) {
                moduleRenderCount++;
                if (moduleRenderCount < showingButtonCount + 1) {
                    moduleButton.setX(X);
                    moduleButton.setY(modY);
                    moduleButton.render(mX, mY);
                    if (!moduleButton.isOpen() && opening && buttonsBeforeClosing.contains(moduleButton)) {
                        moduleButton.processRightClick();
                    }
                    modY += H;
                    if (moduleButton.isOpen() || moduleButton.isOpening() || moduleButton.isClosing()) {
                        int settingRenderCount = 0;
                        for (OptionButton optionButton : moduleButton.getButtons()) {
                            settingRenderCount++;
                            if (settingRenderCount < moduleButton.getShowingModuleCount() + 1) {
                                optionButton.setX(X);
                                optionButton.setY(modY);
                                optionButton.render(mX, mY);
                                modY += H;
                            }
                        }
                    }
                }
            }
        }
        if (opening) {
            showingButtonCount++;
            if (showingButtonCount == buttons.size()) {
                opening = false;
                open = true;
                buttonsBeforeClosing.clear();
            }
        }

        if (closing) {
            showingButtonCount--;
            if (showingButtonCount == 0 || showingButtonCount == 1) {
                closing = false;
                open = false;
            }
        }

    }

    public void mouseDown(int mX, int mY, int mB) {
        if (RenderUtils.isHovered(X, Y, W, H, mX, mY)) {
            if (mB == 0) {
                dragging = true;
                dragX = X - mX;
                dragY = Y - mY;
            }
        }
        if (open) {
            for (HackButton button : buttons) {
                button.mouseDown(mX, mY, mB);
            }
        }
    }

    public void mouseUp(int mX, int mY) {
        dragging = false;
        if (open) {
            for (HackButton button : buttons) {
                button.mouseUp(mX, mY);
            }
        }
    }

    public void keyPress(int key) {
        if (open) {
            for (HackButton button : buttons) {
                button.keyPress(key);
            }
        }
    }

    public void close() {
        for (HackButton button : buttons) {
            button.close();
        }
    }

    public int getY()
    {
        return Y;
    }

    public void setY(int y)
    {
        Y = y;
    }
}
