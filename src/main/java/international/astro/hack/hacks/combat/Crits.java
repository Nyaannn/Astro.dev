package international.astro.hack.hacks.combat;

import international.astro.events.AttackEvent;
import international.astro.events.PacketSendEvent;
import international.astro.hack.Hack;
import international.astro.hack.option.options.OList;
import international.astro.mixins.accessor.ICPacketPlayer;
import international.astro.mixins.accessor.IEntityPlayerSP;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Hack.Construct(name = "Criticals", description = "hit harder", category = Hack.Category.COMBAT)
public class Crits extends Hack {

    OList mode = new OList("Mode", "2b2t","2b2t");

    public Crits() {
        addOption(mode);
    }
    double spoofedY = -1337;

    @SubscribeEvent
    public void onAttack(AttackEvent e){
        if(mode.isMode("2b2t")) {
            ((IEntityPlayerSP) mc.player).setLastReportedPosY(-1337);
        }
    }

    @SubscribeEvent
    public void onSend(PacketSendEvent e){
        if(mode.isMode("2b2t")) {
            if (e.getPacket() instanceof CPacketPlayer) {
                CPacketPlayer p = (CPacketPlayer) e.getPacket();
                if (mc.player.onGround) {
                    if (spoofedY <= 0) {
                        spoofedY = 0.01;
                    } else {
                        spoofedY -= 0.00001;
                    }
                } else {
                    spoofedY = -1337;
                }
                ((ICPacketPlayer) p).setMoving(true);
                ((ICPacketPlayer) p).setOnGround(false);
                if (spoofedY >= 0) {
                    ((ICPacketPlayer) p).setY(spoofedY += p.getY(mc.player.posY));
                }
            }
        }
    }
}
