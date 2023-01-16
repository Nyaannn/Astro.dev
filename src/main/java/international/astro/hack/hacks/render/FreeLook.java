package international.astro.hack.hacks.render;

import international.astro.events.TurnEvent;
import international.astro.hack.Hack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "FreeLook", description = "F5 but better", category = Hack.Category.RENDER)
public class FreeLook extends Hack {

    private float dYaw;
    private float dPitch;

    @SubscribeEvent
    public void onTick(TickEvent e){
        if (nullCheck()) {return;}
        if (mc.gameSettings.thirdPersonView != 1) {
            disable();
        }
    }


    @Override
    public void onEnable() {
        dYaw = 0.0f;
        dPitch = 0.0f;
        mc.gameSettings.thirdPersonView = 1;
        super.onEnable();
    }

    @SubscribeEvent
    public void cameraSetup(EntityViewRenderEvent.CameraSetup event) {
        if(nullCheck()){return;}
        if (mc.gameSettings.thirdPersonView > 0) {
            event.setYaw(event.getYaw() + dYaw);
            event.setPitch(event.getPitch() + dPitch);
        }
    }

    @Override
    public void onDisable() {
        mc.gameSettings.thirdPersonView = 0;
        super.onDisable();
    }

    @SubscribeEvent
    public void onTurn(TurnEvent event) {
        if (mc.gameSettings.thirdPersonView > 0) {
            dYaw = (float)(dYaw + event.getYaw() * 0.15);
            dPitch = (float)(dPitch - event.getPitch() * 0.15);
            dPitch = MathHelper.clamp(dPitch, -90.0f, 90.0f);
            event.setCanceled(true);
        }
    }
}
