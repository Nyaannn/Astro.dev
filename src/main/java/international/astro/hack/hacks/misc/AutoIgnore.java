package international.astro.hack.hacks.misc;

import international.astro.hack.Hack;
import international.astro.hack.option.options.OBoolean;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

@Hack.Construct(name = "AutoIgnore", description = "mutes everyone that speaks", category = Hack.Category.MISC)
public class AutoIgnore extends Hack {


    public static AutoIgnore getInstance = new AutoIgnore();

    public AutoIgnore() {


    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent eve) {
        if (nullCheck()) {return;}
        mc.player.connection.sendPacket(new CPacketChatMessage("/ignore "+eve.getType().name()));



    }
}