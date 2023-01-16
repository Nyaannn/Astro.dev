package international.astro.hack.hacks.movement;

import international.astro.events.PacketReceiveEvent;
import international.astro.hack.Hack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.network.play.server.SPacketEntityStatus;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Hack.Construct(name = "AntiKB", description = "AntiKB", category = Hack.Category.MOVEMENT)
public class AntiKB extends Hack {

    @SubscribeEvent
    public void onBigFuckingExplosion(PacketReceiveEvent event) {
        if (nullCheck()) {return;}
        Entity entity;
        SPacketEntityStatus packet;
        if (event.getPacket() instanceof SPacketEntityVelocity && ((SPacketEntityVelocity) event.getPacket()).getEntityID() == mc.player.getEntityId()) {
            event.setCanceled(true);
            return;
        }
        if (event.getPacket() instanceof SPacketEntityStatus && (packet = (SPacketEntityStatus) event.getPacket()).getOpCode() == 31 && (entity = packet.getEntity(mc.world)) instanceof EntityFishHook) {
            EntityFishHook fishHook = (EntityFishHook) entity;
            if (fishHook.caughtEntity == mc.player) {
                event.setCanceled(true);
            }
        }
        if (event.getPacket() instanceof SPacketExplosion) {
            event.setCanceled(true);
        }

    }
}