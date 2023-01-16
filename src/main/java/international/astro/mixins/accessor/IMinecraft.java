package international.astro.mixins.accessor;

import net.minecraft.util.Timer;

public interface IMinecraft {

    Timer getTimer();

    void setRightClickDelayTimer(int i);
}
