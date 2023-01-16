package international.astro.hack.hacks.random;

import international.astro.hack.Hack;
import international.astro.hack.option.options.OBoolean;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Random;

@Hack.Construct(name = "Spinjitzu", description = "nigjago", category = Hack.Category.RANDOM)
public class Spinjitzu extends Hack {

    public Spinjitzu() {

    }
    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent e) {
        if (nullCheck()) {return;}
        mc.player.rotationYaw =mc.player.rotationYaw+45;
    }
}