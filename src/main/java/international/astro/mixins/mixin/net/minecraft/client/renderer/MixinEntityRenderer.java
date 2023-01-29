package international.astro.mixins.mixin.net.minecraft.client.renderer;

import international.astro.Astro;
import international.astro.hack.hacks.render.NoRender;
import international.astro.hack.hacks.render.ViewClip;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntityRenderer.class})
public class MixinEntityRenderer {

    @Inject(method = {"hurtCameraEffect"}, at = {@At(value = "HEAD")}, cancellable = true)
    public void hurtCameraEffect(float ticks, CallbackInfo info) {
        if (Astro.hackManager.getHack("NoRender").isEnabled() && NoRender.getInstance.hurtCam.isEnabled()) {
            info.cancel();
        }

    }

    @ModifyVariable(method = "orientCamera", at = @At("STORE"), ordinal = 3)
    private double onOrientCameraX(double range) {
        if(Astro.hackManager.getHack("ViewClip").isEnabled()){
            return ViewClip.getInstance.distance.getValue();
        }
        return range;
    }

    @ModifyVariable(method = "orientCamera", at = @At("STORE"), ordinal = 7)
    private double onOrientCameraZ(double range) {
        if(Astro.hackManager.getHack("ViewClip").isEnabled()){
            return ViewClip.getInstance.distance.getValue();
        }
        return range;
    }
}
