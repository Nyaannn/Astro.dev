package international.astro.hack.hacks.render;

import international.astro.hack.Hack;
import international.astro.hack.option.options.OBoolean;

@Hack.Construct(name = "NoRender", description = "stop rendering stuff", category = Hack.Category.RENDER)
public class NoRender extends Hack {
    public OBoolean hurtCam = new OBoolean("NoHurtCam", true);
    public OBoolean weather = new OBoolean("NoWeather",  true);

    public static NoRender getInstance = new NoRender();

    public NoRender(){
        addOption(hurtCam);
        addOption(weather);
    }

    @Override
    public void onEnable() {
        if(weather.isEnabled()) {
            mc.world.setRainStrength(0);
            mc.world.setThunderStrength(0);
        }
        super.onEnable();
    }
}
