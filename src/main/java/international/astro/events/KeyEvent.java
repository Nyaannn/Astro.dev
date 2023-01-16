package international.astro.events;

import international.astro.Astro;
import international.astro.hack.Hack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class KeyEvent {

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if(!Keyboard.getEventKeyState()){return;}
        for (Hack hack : Astro.hackManager.getHacks()) {
            if (hack.getBind() == Keyboard.getEventKey()) {
                hack.toggle();
            }
        }
    }
}
