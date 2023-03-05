package international.astro.hack.hacks.render;

import international.astro.hack.Hack;

@Hack.Construct(name = "FullBright", description = "see in the dark", category = Hack.Category.RENDER)
public class FullBright extends Hack {

    float oldGamma;

    @Override
    public void onEnable()	{
        oldGamma = mc.gameSettings.gammaSetting;
        mc.gameSettings.gammaSetting = 500;
    }
    @Override
    public void onDisable()	{
        mc.gameSettings.gammaSetting = oldGamma;
    }

}
