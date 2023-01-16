package international.astro.graphics.clickgui;

import international.astro.Astro;
import international.astro.hack.Hack;
import net.minecraft.client.Minecraft;

import java.awt.*;


public class OptionButton {
    public final Minecraft mc = Minecraft.getMinecraft();
    private final int H;
    private Hack hack;
    private int X;
    private int Y;
    private int W;


    public OptionButton(Hack hack, int x, int y, int w, int h) {
        this.hack = hack;
        X = x;
        Y = y;
        W = w;
        H = h;
    }

    public void render(int mX, int mY) {}

    public void mouseDown(int mX, int mY, int mB) {}

    public void mouseUp(int mX, int mY) {}

    public void keyPress(int key) {}

    public void close() {}

    public void drawButton(int mX, int mY) {
        if (hack.isEnabled()) {
            Astro.clickGui.drawGradient(X, Y, X + W, Y + H, new Color(150, 150, 250, 225).getRGB(), new Color(150, 150, 250, 225).getRGB());
            Astro.clickGui.drawGradient(X, Y, X + 2, Y + H, new Color(120, 120, 210, 225).getRGB(), new Color(120, 120, 210, 225).getRGB());
        } else {
            Astro.clickGui.drawGradient(X, Y, X + W, Y + H, new Color(70, 70, 70, 225).getRGB(), new Color(70, 70, 70, 225).getRGB());
            Astro.clickGui.drawGradient(X, Y, X + 2, Y + H, new Color(120, 120, 210, 225).getRGB(), new Color(120, 120, 210, 225).getRGB());
        }
    }

    public Hack getHack() {
        return hack;
    }

    public void setModule(Hack hack) {
        this.hack = hack;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getW() {
        return W;
    }

    public int getH() {
        return H;
    }
}
