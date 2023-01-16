package international.astro.hack.hacks.client;

import com.mojang.realmsclient.gui.ChatFormatting;
import international.astro.Astro;
import international.astro.hack.Hack;
import international.astro.hack.HackManager;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


@Hack.Construct(name = "HUD", description = "hud thingy", category = Hack.Category.CLIENT)
public class HUD extends Hack {

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post e) {
        if(nullCheck()) return;
        if (e.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            Astro.font.drawStringWidthShadow("Welcome to "+Astro.NAME + " B" + Astro.VERSION+" [commit "+Astro.getSimpleGitHash()+"] "+mc.player.getName()+"!", 2, 2, 0xff7878FF);
            Astro.font.drawString("Position: "+"X:"+Math.round(mc.player.posX)+" Y:"+Math.round(mc.player.posY)+" Z:"+Math.round(mc.player.posZ), 2, 12, 0xff7878FF);
            Astro.font.drawString("Health: "+Math.round(mc.player.getHealth()), 2, 22, 0xff7878FF);
            Astro.font.drawString("Dimension: "+mc.player.dimension, 2, 32, 0xff7878FF);
            ScaledResolution sr = new ScaledResolution(mc);
            int y = 2;
            final ArrayList<String> list = new ArrayList<String>();
            for (final Hack hack : Astro.hackManager.getHacks()) {
                if (hack.isEnabled()) {
                    list.add(hack.getName());
                }
                list.sort(Comparator.comparingInt(s -> mc.fontRenderer.getStringWidth(s)));
                Collections.reverse(list);
            }
            for (final String name : list) {
                Astro.font.drawString(name, (float) ((sr.getScaledWidth() - Astro.font.getStringWidth(name)) - 0.5), y + 2, 0xff7878FF);
                y += 10;
            }
        }
    }
}
