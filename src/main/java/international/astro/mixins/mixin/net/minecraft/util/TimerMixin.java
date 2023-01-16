package international.astro.mixins.mixin.net.minecraft.util;

import international.astro.mixins.accessor.ITimer;
import net.minecraft.util.Timer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({Timer.class})
public abstract class TimerMixin implements ITimer {

    @Override
    @Accessor()
    public abstract void setTickLength(float f);
}
