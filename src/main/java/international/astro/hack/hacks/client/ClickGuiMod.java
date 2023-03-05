package international.astro.hack.hacks.client;

import international.astro.Astro;
import international.astro.hack.Hack;
import international.astro.hack.option.options.OBoolean;
<<<<<<< HEAD
import international.astro.util.file.Config;
=======
>>>>>>> 54c4a826ad0df3f3e9b646312425ccd46dd7d76c
import org.lwjgl.input.Keyboard;

@Hack.Construct(name = "ClickGui", description = "Click dat Gui", category = Hack.Category.CLIENT)

public class ClickGuiMod extends Hack {

    public static OBoolean blur = new OBoolean("Blur", false);

    public ClickGuiMod() {
        setBind(Keyboard.KEY_RSHIFT);
        addOption(blur);
    }


    @Override
    public void onEnable() {
        Config.saveConfig();
        mc.displayGuiScreen(Astro.clickGui);
        super.onEnable();
    }
}
