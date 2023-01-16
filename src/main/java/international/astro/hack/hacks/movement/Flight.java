package international.astro.hack.hacks.movement;

import international.astro.hack.Hack;
import international.astro.hack.option.options.OList;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "Flight", description = "Fly", category = Hack.Category.MOVEMENT)
public class Flight extends Hack {
    public OList mode = new OList("Mode", "Vanilla", "Vanilla","Minemora");
    public static Flight getInstance = new Flight();
    int glideDelay;

    public Flight(){
        addOption(mode);
    }

    @Override
    public void onEnable() {
        if(mode.isMode("Vanilla")) {
            mc.player.capabilities.isFlying = true;
            if (mc.player.capabilities.isCreativeMode) {
                return;
            }
            mc.player.capabilities.allowFlying = true;
            mc.player.capabilities.setFlySpeed(20);
            super.onEnable();
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent e){
        if(mode.isMode("Minemora")) {
            mc.player.motionY = -0.07;
            if (!mc.player.onGround) {
                glideDelay++;
            }
            if (glideDelay >= 2 && !mc.player.onGround) {
                mc.player.motionY = 0.7 / 10;
                glideDelay = 0;
            }
        }
    }

    @Override
    public void onDisable() {
        mc.player.capabilities.isFlying=false;
        mc.player.capabilities.allowFlying = false;
        super.onDisable();
    }
}
