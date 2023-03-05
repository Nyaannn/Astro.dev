package international.astro.hack.hacks.client;

import com.mojang.realmsclient.gui.ChatFormatting;
import international.astro.Astro;
import international.astro.hack.Hack;
import international.astro.hack.HackManager;
import international.astro.hack.option.options.ODouble;
import international.astro.util.RenderUtils;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


@Hack.Construct(name = "HUD", description = "hud thingy", category = Hack.Category.CLIENT)
public class HUD extends Hack {
    ODouble wx = new ODouble("WaterMarkX", 0,500,1,0);
    ODouble wy = new ODouble("WaterMarkY", 0,500,1,0);

    public HUD(){
        addOption(wx);
        addOption(wy);
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post e) {
        if (nullCheck()) return;
        ScaledResolution sr = new ScaledResolution(mc);
        if (e.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            String watermark = Astro.NAME + " b"+Astro.VERSION;
            if(Astro.hackManager.getHack("CustomFont").isEnabled()) {
                Astro.font.drawString(watermark, wx.getFloatValue(), wy.getFloatValue(), Astro.colorManager.getRGBA());
            }else{
                mc.fontRenderer.drawStringWithShadow(watermark, wx.getIntValue(), wy.getIntValue(), Astro.colorManager.getRGBA());
            }
            int y = 2;
            final ArrayList<String> list = new ArrayList<>();
            for (final Hack hack : Astro.hackManager.getEnabledHacks()) {
                list.add(hack.getName());
            }
            list.sort(Comparator.comparingInt(s -> Astro.font.getStringWidth(s)));
            Collections.reverse(list);
            for (final String name : list) {
                if(Astro.hackManager.getHack("CustomFont").isEnabled()) {
                    Astro.font.drawString(name, (float) ((sr.getScaledWidth() - Astro.font.getStringWidth(name)) - 1.5), y + 2, Astro.colorManager.getRGBA());
                }else{
                    mc.fontRenderer.drawStringWithShadow(name,(sr.getScaledWidth() - Astro.font.getStringWidth(name)) - 3, y + 2, Astro.colorManager.getRGBA());
                }
                y += 10;
            }
        }
    }
}
