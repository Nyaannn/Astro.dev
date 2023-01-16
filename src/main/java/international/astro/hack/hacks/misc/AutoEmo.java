package international.astro.hack.hacks.misc;

import international.astro.hack.Hack;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Hack.Construct(name = "AutoEmo", description = "emo", category = Hack.Category.EXPLOIT)
public class AutoEmo extends Hack {
    public static AutoEmo getInstance = new AutoEmo();
    public AutoEmo() {
    }
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent eve) {
        if (nullCheck()) {return;}
        mc.player.connection.sendPacket(new CPacketChatMessage("/kill"));
    }
}