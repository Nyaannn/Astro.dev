package international.astro.hack.hacks.movement;


import international.astro.events.PacketSendEvent;
import international.astro.hack.Hack;
import net.minecraft.network.play.client.CPacketCloseWindow;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "NoFall", description = "no fall dmg", category = Hack.Category.MOVEMENT)
public class NoFall extends Hack {

    public NoFall() {
    }

    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event){
        if(nullCheck()){return;}
        if(mc.player.fallDistance > 5) {
            mc.getConnection().sendPacket(new CPacketPlayer(true));
        }
    }
}