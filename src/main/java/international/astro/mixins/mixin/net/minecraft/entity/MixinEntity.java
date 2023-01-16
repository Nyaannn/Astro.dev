package international.astro.mixins.mixin.net.minecraft.entity;

import international.astro.events.TurnEvent;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Entity.class})
public abstract class MixinEntity {
    @Inject(method={"turn"}, at={@At(value="HEAD")}, cancellable=true)
    public void onTurnHook(float yaw, float pitch, CallbackInfo ci) {
        TurnEvent e = new TurnEvent(yaw, pitch);
        MinecraftForge.EVENT_BUS.post(e);
        if (e.isCanceled()) {
            ci.cancel();
        }
    }
}
