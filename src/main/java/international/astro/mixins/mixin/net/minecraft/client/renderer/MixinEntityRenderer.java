package international.astro.mixins.mixin.net.minecraft.client.renderer;

import international.astro.Astro;
import international.astro.hack.hacks.render.ViewTweaks;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin({EntityRenderer.class})
public class MixinEntityRenderer {

    @ModifyVariable(method = "orientCamera", at = @At("STORE"), ordinal = 3)
    private double onOrientCameraX(double range) {
        if(Astro.hackManager.getHack("ViewTweaks").isEnabled() && ViewTweaks.getInstance.viewClip.isEnabled()){
            return 5;
        }
        return range;
    }

    @ModifyVariable(method = "orientCamera", at = @At("STORE"), ordinal = 7)
    private double onOrientCameraZ(double range) {
        if(Astro.hackManager.getHack("ViewTweaks").isEnabled() && ViewTweaks.getInstance.viewClip.isEnabled()){
            return 5;
        }
        return range;
    }
}
