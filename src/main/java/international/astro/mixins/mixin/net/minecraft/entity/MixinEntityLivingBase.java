package international.astro.mixins.mixin.net.minecraft.entity;

import international.astro.Astro;
import international.astro.hack.hacks.render.ViewTweaks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({EntityLivingBase.class})
public abstract class MixinEntityLivingBase extends Entity {

    public MixinEntityLivingBase(World p_i1582_1_) {
        super(p_i1582_1_);
    }

    @Inject(method = { "getArmSwingAnimationEnd" }, at = { @At("HEAD") }, cancellable = true)
    private void getArmSwingAnimationEnd(final CallbackInfoReturnable<Integer> info) {
        if (Astro.hackManager.getHack("ViewTweaks").isEnabled()) {
            info.setReturnValue(ViewTweaks.getInstance.swingSpeed.getIntValue());
        }
    }
}
