package international.astro.util;

import international.astro.Astro;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Base64;

public class RenderUtils {

    public static Minecraft mc = Minecraft.getMinecraft();
    public static final String X16 = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAACsElEQVQ4jWWTzWskdRCGn6rqTk/PV3o20XysG/XgJCyie1TwCy+K4EFBvXoRDwviTVbwJoIX8R/wpODJf0EEPXhYyMENssoSlgXdHDKZ2D2T7unuX3mIkWH3vVRRvPVCwVPCQ8qyXk8+nM/K15EqMzNr23DHvf0Z+Oph/5KSJPlIJDlR23LVKy50XaTjIpmrdlxEPY7j68s7dtGo6jdtGz4163SQISDEcQ/3LrCJyBShBdc3RFzd/af/U5T0Y5XUI1t1s0tuuu4iOy665zZ606P4GVdNXURdNfI47jjwFoBAlqnqIW6ZCiANoa2xZIPh+DVO7h0i+SEix4QwwUMEtCDt1N3XFZL3o3g9ExuADQjeR2yDl97+gKieEqa3acP8/BRPURXMIlQsU/S6iex+EkK6p9InBEN1CNKjrc74+899nIooMlQNvCJ4jXtNvBLThqaK0MGzIqu0oeHdd65x//5fzGanjMeXeXK7w2Qyp9uLef65p7h5c5/T02MODv6gaRrAn4i4/HRhcUpbVgwe22FhxpX+Lr0kULYZm9sVk+Oco6OcXnfIeHyVW7cKQmgQ+YcoGfWPk0tXWdQV3/3agj+KeE0INfV8hpwVkBeEskSqM7z5DbNNoshpFsndKJrc/SVm7ZVBtg4SA9Bf2wIVzsqC2fyERV3SNAua+YywmBEWC3xe4tN8X8iuZdpsnHjtCCDWRUdrjB7fI3JjNBqBGKuPbHPv9j5FkVPNc+riaJoM6rGcU9j5wr29IZIgtoIHxdIdNN1COymmFdrkDNKSWVEwrxqC22fN9OBzllD+XkRcRP28iouuuPZecF150UX3XGT1Pxr163MIl37B3X9QVQN5GfxiCnWL0AUCYFP39oZ7+SVQc5HygMag7wn2KqK7eBKrDX934cfQ5N/C6Z1l878ykTIdTzpO4QAAAABJRU5ErkJggg==";
    public static final String X32 = "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";

    public static void drawImage(int x, int y, int w, int h, int color, String location) {
        if (color == 0) {GlStateManager.color(1, 1, 1);}
        else {color(color);}
        ResourceLocation resourceLocation = new ResourceLocation(location);
        mc.getTextureManager().bindTexture(resourceLocation);
        GlStateManager.enableBlend();
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0F, 0.0F, w, h, w, h);
        GlStateManager.disableBlend();
    }

    public static void color(int color) {
        float alpha = (color >> 24 & 255) / 255f;
        float red = (color >> 16 & 255) / 255f;
        float green = (color >> 8 & 255) / 255f;
        float blue = (color & 255) / 255f;
        GlStateManager.color(red, green, blue, alpha);
    }

    public static void drawCircle(float x, float y, float r, int h, int j, int color) {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);

        color(color);

        float var;
        GL11.glVertex2f(x, y);
        for (var = h; var <= j; var++) {
            color(color);
            GL11.glVertex2f((float) (r * Math.cos(Math.PI * var / 180) + x), (float) (r * Math.sin(Math.PI * var / 180) + y));
        }
        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_BLEND);
    }

    public static void drawRoundedRectangle(int x, int y, int w, int h, int radius, int color) {
        Gui.drawRect(x + radius, y + radius, x + w - radius, y + h - radius, color);
        Gui.drawRect(x + radius, y, x + w - radius, y + radius, color);
        Gui.drawRect(x + w - radius, y + radius, x + w, y + h - radius, color);
        Gui.drawRect(x + radius, y + h - radius, x + w - radius, y + h, color);
        Gui.drawRect(x, y + radius, x + radius, y + h - radius, color);
        drawCircle(x + radius, y + radius, radius, 180, 270, color);
        drawCircle(x + w - radius, y + radius, radius, 270, 360, color);
        drawCircle(x + radius, y + h - radius, radius, 90, 180, color);
        drawCircle(x + w - radius, y + h - radius, radius, 0, 90, color);
    }

    public static boolean isHovered(int X, int Y, int W, int H, int mX, int mY) {
        return mX >= X && mX <= X + W && mY >= Y && mY <= Y + H;
    }

    public static int getBackgroundColor() {
        return Integer.MIN_VALUE;
    }


    public static ByteArrayInputStream convertIcon(String s){
        Base64.Decoder decoder = Base64.getDecoder();
        return new ByteArrayInputStream(decoder.decode(s));
    }

    public static ByteBuffer readImageToBuffer(InputStream imageStream) throws IOException {
        BufferedImage bufferedimage = ImageIO.read(imageStream);
        int[] aint = bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), (int[]) null, 0, bufferedimage.getWidth());
        ByteBuffer bytebuffer = ByteBuffer.allocate(4 * aint.length);
        for (int i : aint) {bytebuffer.putInt(i << 8 | i >> 24 & 255);}
        ((Buffer)bytebuffer).flip();
        return bytebuffer;
    }

    public static void setWindowIcon() {
        if (Util.getOSType() != Util.EnumOS.OSX) {
            try {
                ByteBuffer[] icons = new ByteBuffer[]{readImageToBuffer(convertIcon(X16)), readImageToBuffer(convertIcon(X32))};
                Display.setIcon(icons);
            } catch (Exception e) {
                Astro.LOGGER.error("Windows Icon Did Not Set", (Throwable) e);
            }
        }
    }
}