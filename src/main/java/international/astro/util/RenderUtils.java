package international.astro.util;

import international.astro.Astro;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.Objects;

public class RenderUtils {

    public static Minecraft mc = Minecraft.getMinecraft();
    public static ICamera camera = new Frustum();
    public static String X16 = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAACsElEQVQ4jWWTzWskdRCGn6rqTk/PV3o20XysG/XgJCyie1TwCy+K4EFBvXoRDwviTVbwJoIX8R/wpODJf0EEPXhYyMENssoSlgXdHDKZ2D2T7unuX3mIkWH3vVRRvPVCwVPCQ8qyXk8+nM/K15EqMzNr23DHvf0Z+Oph/5KSJPlIJDlR23LVKy50XaTjIpmrdlxEPY7j68s7dtGo6jdtGz4163SQISDEcQ/3LrCJyBShBdc3RFzd/af/U5T0Y5XUI1t1s0tuuu4iOy665zZ606P4GVdNXURdNfI47jjwFoBAlqnqIW6ZCiANoa2xZIPh+DVO7h0i+SEix4QwwUMEtCDt1N3XFZL3o3g9ExuADQjeR2yDl97+gKieEqa3acP8/BRPURXMIlQsU/S6iex+EkK6p9InBEN1CNKjrc74+899nIooMlQNvCJ4jXtNvBLThqaK0MGzIqu0oeHdd65x//5fzGanjMeXeXK7w2Qyp9uLef65p7h5c5/T02MODv6gaRrAn4i4/HRhcUpbVgwe22FhxpX+Lr0kULYZm9sVk+Oco6OcXnfIeHyVW7cKQmgQ+YcoGfWPk0tXWdQV3/3agj+KeE0INfV8hpwVkBeEskSqM7z5DbNNoshpFsndKJrc/SVm7ZVBtg4SA9Bf2wIVzsqC2fyERV3SNAua+YywmBEWC3xe4tN8X8iuZdpsnHjtCCDWRUdrjB7fI3JjNBqBGKuPbHPv9j5FkVPNc+riaJoM6rGcU9j5wr29IZIgtoIHxdIdNN1COymmFdrkDNKSWVEwrxqC22fN9OBzllD+XkRcRP28iouuuPZecF150UX3XGT1Pxr163MIl37B3X9QVQN5GfxiCnWL0AUCYFP39oZ7+SVQc5HygMag7wn2KqK7eBKrDX934cfQ5N/C6Z1l878ykTIdTzpO4QAAAABJRU5ErkJggg==";
    public static String X32 = "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";

    public static void drawImage(int x, int y, int w, int h, int color, String location) {
        if (color == 0) {GlStateManager.color(1, 1, 1);}
        else {color(new Color(color));}
        ResourceLocation resourceLocation = new ResourceLocation(location);
        mc.getTextureManager().bindTexture(resourceLocation);
        GlStateManager.enableBlend();
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0F, 0.0F, w, h, w, h);
        GlStateManager.disableBlend();
    }

    public static void color(Color color) {
        if (color == null) {color = Color.white;}
        GL11.glColor4d(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, color.getAlpha() / 255F);
    }

    public static boolean isHovered(int X, int Y, int W, int H, int mX, int mY) {
        return mX >= X && mX <= X + W && mY >= Y && mY <= Y + H;
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

    public static void drawBoxESP(BlockPos pos, Color color, float lineWidth, boolean outline, boolean box, int boxAlpha) {
        AxisAlignedBB bb = new AxisAlignedBB((double) pos.getX() - mc.getRenderManager().viewerPosX, (double) pos.getY() - mc.getRenderManager().viewerPosY, (double) pos.getZ() - mc.getRenderManager().viewerPosZ, (double) (pos.getX() + 1) - mc.getRenderManager().viewerPosX, (double) (pos.getY() + 1) - mc.getRenderManager().viewerPosY, (double) (pos.getZ() + 1) - mc.getRenderManager().viewerPosZ);
        camera.setPosition(Objects.requireNonNull(mc.getRenderViewEntity()).posX, mc.getRenderViewEntity().posY, mc.getRenderViewEntity().posZ);
        if (camera.isBoundingBoxInFrustum(new AxisAlignedBB(bb.minX + mc.getRenderManager().viewerPosX, bb.minY + mc.getRenderManager().viewerPosY, bb.minZ + mc.getRenderManager().viewerPosZ, bb.maxX + mc.getRenderManager().viewerPosX, bb.maxY + mc.getRenderManager().viewerPosY, bb.maxZ + mc.getRenderManager().viewerPosZ))) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask(false);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            GL11.glLineWidth(lineWidth);
            if (box) {
                RenderGlobal.renderFilledBox(bb, (float) color.getRed() / 255.0f, (float) color.getGreen() / 255.0f, (float) color.getBlue() / 255.0f, (float) boxAlpha / 255.0f);
            }
            if (outline) {
                RenderGlobal.drawBoundingBox(bb.minX, bb.minY, bb.minZ, bb.maxX, bb.maxY, bb.maxZ, (float) color.getRed() / 255.0f, (float) color.getGreen() / 255.0f, (float) color.getBlue() / 255.0f, (float) color.getAlpha() / 255.0f);
            }
            GL11.glDisable(2848);
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }

    public static void rect(double x, double y, double width, double height, Color color) {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GlStateManager.disableAlpha();
        GlStateManager.disableDepth();
        if (color != null)
            color(color);
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x + width, y);
        GL11.glVertex2d(x + width, y + height);
        GL11.glVertex2d(x, y + height);
        GL11.glEnd();
        GlStateManager.enableAlpha();
        GlStateManager.enableDepth();
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        color(Color.white);
    }

    public static void roundedRect(double x, double y, double width, double height, double edgeRadius, Color color) {
        double halfRadius = edgeRadius / 2;
        width -= halfRadius;
        height -= halfRadius;
        float sideLength = (float) edgeRadius;
        sideLength /= 2;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GlStateManager.disableAlpha();
        GlStateManager.disableDepth();
        if (color != null)
            color(color);
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        for (double i = 180; i <= 270; i++) {
            double angle = i * (Math.PI * 2) / 360;
            GL11.glVertex2d(x + (sideLength * Math.cos(angle)) + sideLength, y + (sideLength * Math.sin(angle)) + sideLength);
        }
        GL11.glVertex2d(x + sideLength, y + sideLength);
        GL11.glEnd();
        GlStateManager.enableAlpha();
        GlStateManager.enableDepth();
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        color(Color.white);
        sideLength = (float) edgeRadius;
        sideLength /= 2;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GlStateManager.disableAlpha();
        GlStateManager.disableDepth();
        if (color != null)
            color(color);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        for (double i = 0; i <= 90; i++) {
            double angle = i * (Math.PI * 2) / 360;
            GL11.glVertex2d(x + width + (sideLength * Math.cos(angle)), y + height + (sideLength * Math.sin(angle)));
        }
        GL11.glVertex2d(x + width, y + height);
        GL11.glEnd();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GlStateManager.enableAlpha();
        GlStateManager.enableDepth();
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        color(Color.white);
        sideLength = (float) edgeRadius;
        sideLength /= 2;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GlStateManager.disableAlpha();
        GlStateManager.disableDepth();
        if (color != null)
            color(color);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        for (double i = 270; i <= 360; i++) {
            double angle = i * (Math.PI * 2) / 360;
            GL11.glVertex2d(x + width + (sideLength * Math.cos(angle)), y + (sideLength * Math.sin(angle)) + sideLength);
        }
        GL11.glVertex2d(x + width, y + sideLength);

        GL11.glEnd();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GlStateManager.enableAlpha();
        GlStateManager.enableDepth();
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        color(Color.white);
        sideLength = (float) edgeRadius;
        sideLength /= 2;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GlStateManager.disableAlpha();
        GlStateManager.disableDepth();
        if (color != null)
            color(color);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        for (double i = 90; i <= 180; i++) {
            double angle = i * (Math.PI * 2) / 360;
            GL11.glVertex2d(x + (sideLength * Math.cos(angle)) + sideLength, y + height + (sideLength * Math.sin(angle)));
        }
        GL11.glVertex2d(x + sideLength, y + height);

        GL11.glEnd();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GlStateManager.enableAlpha();
        GlStateManager.enableDepth();
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        color(Color.white);
        rect(x + halfRadius, y + halfRadius, width - halfRadius, height - halfRadius, color);
        rect(x, y + halfRadius, edgeRadius / 2, height - halfRadius, color);
        rect(x + width, y + halfRadius, edgeRadius / 2, height - halfRadius, color);
        rect(x + halfRadius, y, width - halfRadius, halfRadius, color);
        rect(x + halfRadius, y + height, width - halfRadius, halfRadius, color);
    }


}
