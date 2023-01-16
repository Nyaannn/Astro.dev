package international.astro.hack.hacks.combat;

import international.astro.events.PacketSendEvent;
import international.astro.hack.Hack;
import international.astro.hack.option.options.OBoolean;
import international.astro.hack.option.options.ODouble;
import international.astro.hack.option.options.OList;
import international.astro.mixins.accessor.ICPacketPlayer;
import international.astro.util.PlayerUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Hack.Construct(name = "KillAura", description = "killing aura(real)", category = Hack.Category.COMBAT)
public class KillAura extends Hack {
    OList mode = new OList("Mode", "Closest", "Multi", "Closest");
    ODouble range = new ODouble("Range", 4, 7, 1, 5);

    int delay = 0;
    float yaw;
    float pitch;
    public KillAura(){
    addOption(mode);
    addOption(range);
    }
    @Override
    public void onDisable() {

    }

    @SubscribeEvent
    public void onUpdate(final TickEvent.ClientTickEvent event){
        if (nullCheck()) {return;}
        if (mc.player.getCooledAttackStrength(0) == 1) {
            delay++;
            if(delay >= 20) {
                if(mode.getMode().equalsIgnoreCase("Multi")) {
                    attackTargetMulti();
                }else {
                    attackTargetClosest();
                }
            }
        }
    }

    public void attackTargetClosest() {
        List<EntityPlayer> closestPlayer = (List<EntityPlayer>) mc.world.playerEntities.stream().filter(player -> player.getDistance(mc.player) < range.getIntValue() && player != mc.player && player.isEntityAlive()).collect(Collectors.toList());
        closestPlayer.sort(Comparator.comparingDouble(player -> player.getDistanceSq(mc.player)));
        if(!closestPlayer.isEmpty()) {
            EntityPlayer p = closestPlayer.get(0);
            yaw = PlayerUtil.getRotations(p)[0];
            pitch = PlayerUtil.getRotations(p)[1];
            mc.playerController.attackEntity(mc.player, p);
            mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }

    private void  attackTargetMulti() {
        for(Entity p : mc.world.loadedEntityList) {
            if(p instanceof EntityPlayer && p != mc.player) {
                if(mc.player.getDistance(p) <= range.getValue()) {
                    yaw = PlayerUtil.getRotations(p)[0];
                    pitch = PlayerUtil.getRotations(p)[1];
                    mc.playerController.attackEntity(mc.player, p);
                    mc.player.swingArm(EnumHand.MAIN_HAND);
                }
            }
        }
    }

    @SubscribeEvent
    public void onUpdate(PacketSendEvent event) {
        if (nullCheck()) {return;}
        if (event.getPacket() instanceof CPacketPlayer) {
            ((ICPacketPlayer) event.getPacket()).setYaw(yaw);
            ((ICPacketPlayer) event.getPacket()).setPitch(pitch);
        }
    }

}