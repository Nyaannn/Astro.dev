package international.astro.hack.hacks.client;

import international.astro.Astro;
import international.astro.hack.Hack;
import org.lwjgl.input.Keyboard;

@Hack.Construct(name = "ClickGui", description = "Click dat Gui", category = Hack.Category.CLIENT)

public class ClickGuiMod extends Hack {


    public ClickGuiMod() {
        setBind(Keyboard.KEY_RSHIFT);
    }


    @Override
    public void onEnable() {
        mc.displayGuiScreen(Astro.clickGui);
        super.onEnable();
    }
}
