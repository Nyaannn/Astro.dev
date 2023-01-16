package international.astro.graphics.clickgui.comp;
import international.astro.Astro;
import international.astro.graphics.clickgui.OptionButton;
import international.astro.hack.Hack;
import international.astro.hack.option.Option;
import international.astro.hack.option.options.ODouble;
import international.astro.util.RenderUtils;
import net.minecraft.client.gui.Gui;
import scala.Int;
import scala.tools.cmd.gen.AnyValReps;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Slider extends OptionButton {
    public final ODouble option;
    protected boolean dragging;
    protected int sliderWidth;

    public Slider(Hack hack, Option option, int X, int Y, int W, int H) {
        super(hack, X, Y, W, H);
        this.dragging = false;
        this.sliderWidth = 0;
        this.option = (ODouble) option;
    }

    @Override
    public void render(int mX, int mY) {
        double diff = Math.min(getW(), Math.max(0, mX - getX()));
        if (dragging) {
            if(diff == 0){
                option.setValue(option.getMin());
            }else{
                option.setValue(roundToPlace(((diff/ getW()) * (option.getMax() - option.getMin()) + option.getMin()), 1));
            }
        }
        sliderWidth = (int) ((getW() - 6) * (option.getValue() - option.getMin()) / (option.getMax() - option.getMin()));
        Astro.clickGui.drawRect(getX(), getY(), getX() + getW(), getY() + getH(), new Color(0, 0, 0,180).getRGB());
        Astro.clickGui.drawRect(getX() + 2, getY(), getX() + sliderWidth+4, getY() + getH(), Astro.colorManager.getRGBA());
        Astro.font.drawString(option.getName(), (float) (getX() + 3), (float) (getY() + 4), new Color(255, 255, 255, 255).getRGB());
        Astro.font.drawString(String.valueOf(option.getValue()), (float) ((getX() + getW()) - mc.fontRenderer.getStringWidth(String.valueOf(option.getValue()))) - 2, (float) (getY() + 4), new Color(255, 255, 255, 255).getRGB());
        Gui.drawRect(getX(), getY(), getX() + 1, getY() + 15, Astro.colorManager.getRGBA());
        Gui.drawRect(getX() + 104, getY(), getX() + 105, getY() + 15, Astro.colorManager.getRGBA());
    }

    @Override
    public void mouseDown(int mX, int mY, int mB) {
        if (RenderUtils.isHovered(getX(), getY(), getW(), getH() - 1, mX, mY)) {
            dragging = true;
        }
    }

    @Override
    public void mouseUp(int mouseX, int mouseY) {
        dragging = false;
        render(mouseX, mouseY );
    }

    @Override
    public void close() {
        dragging = false;
    }

    public double roundToPlace(double value, int place){
        if(place < 0){
            return value;
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(place, RoundingMode.HALF_DOWN);
        return bd.doubleValue();
    }

}
