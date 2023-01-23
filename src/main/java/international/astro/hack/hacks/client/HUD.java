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
        if (nullCheck()) return;
        if (e.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            Astro.font.drawStringWidthShadow(Astro.NAME + " B" + Astro.VERSION, 2, 2, Astro.colorManager.getRGBA());
            ScaledResolution sr = new ScaledResolution(mc);
            int y = 2;
            final ArrayList<String> list = new ArrayList<>();
            for (final Hack hack : Astro.hackManager.getEnabledHacks()) {
                list.add(hack.getName());
            }
            list.sort(Comparator.comparingInt(s -> Astro.font.getStringWidth(s)));
            Collections.reverse(list);
            for (final String name : list) {
                Astro.font.drawString(name, (float) ((sr.getScaledWidth() - Astro.font.getStringWidth(name)) - 1.5), y + 2, Astro.colorManager.getRGBA());
                y += 10;
            }
        }
    }
}
