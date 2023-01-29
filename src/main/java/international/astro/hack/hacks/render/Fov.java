package international.astro.hack.hacks.render;

import international.astro.hack.Hack;
import international.astro.hack.option.options.ODouble;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "Fov", description = "change fov", category = Hack.Category.RENDER)
public class Fov extends Hack {
    ODouble fovSlider = new ODouble("Fov", 75, 150, 1, 120);public ODouble swingSpeed = new ODouble("Time", 1, 50, 1, 6);
    public Fov(){
        addOption(fovSlider);
    }


    @SubscribeEvent
    public void onTick(TickEvent event){
        mc.gameSettings.fovSetting = fovSlider.getIntValue();
    }

}
