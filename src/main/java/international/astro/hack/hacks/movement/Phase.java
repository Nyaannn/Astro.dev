package international.astro.hack.hacks.movement;

import international.astro.hack.Hack;
import international.astro.hack.option.options.OList;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "Phase", description = "Phase n shit", category = Hack.Category.MOVEMENT)
public class Phase extends Hack {
    public OList mode = new OList("Mode", "Boat", "Boat");
    public static Phase getInstance = new Phase();

    public Phase() {
        addOption(mode);
    }

    @Override
    public void onDisable() {
        if(mc.player.isRiding()) {
            mc.player.getRidingEntity().setNoGravity(false);
            mc.player.getRidingEntity().noClip = false;
            mc.player.noClip = false;
        }
    }

    @Override
    public void onEnable() {
        if(nullCheck()){return;}
        if(mode.isMode("Boat")) {
            if (mc.player.isRiding()) {
                mc.player.getRidingEntity().setNoGravity(true);
                mc.player.getRidingEntity().noClip = true;
                mc.player.noClip = true;
            }
        }
    }

}