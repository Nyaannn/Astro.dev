package international.astro.hack.hacks.misc;

import international.astro.events.PacketSendEvent;
import international.astro.hack.Hack;
import international.astro.hack.option.options.OBoolean;
import international.astro.mixins.accessor.ICPacketPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemExpBottle;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "FootExp", description = "xp too yo feet", category = Hack.Category.MISC)
public class FootExp extends Hack {

    @SubscribeEvent
    public void onPacketSend(PacketSendEvent e){
        if(nullCheck()){return;}
        if (e.getPacket() instanceof CPacketPlayer && mc.player.getHeldItemMainhand().getItem() instanceof ItemExpBottle) {
            CPacketPlayer packet = (CPacketPlayer) e.getPacket();
            ((ICPacketPlayer)packet).setPitch(90.0f);
        }
    }

}
