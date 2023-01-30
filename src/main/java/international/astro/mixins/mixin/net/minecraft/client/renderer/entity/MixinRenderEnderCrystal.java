package international.astro.mixins.mixin.net.minecraft.client.renderer.entity;

import international.astro.Astro;
import international.astro.hack.hacks.render.CrystalModifier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderEnderCrystal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
@Mixin({RenderEnderCrystal.class})
public class MixinRenderEnderCrystal {

    protected Minecraft mc = Minecraft.getMinecraft();

    @Redirect(method = "doRender", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V"))
    public void doRender(ModelBase modelBase, Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if(Astro.hackManager.getHack("CrystalModifier").isEnabled()) {
            float defSpin = ((EntityEnderCrystal)entity).innerRotation+mc.getRenderPartialTicks();
            float newSpin = defSpin*CrystalModifier.spinSpeed.getFloatValue();
            float defBounce = MathHelper.sin(defSpin * 0.2f) / 2.0f + 0.5f;
            defBounce += defBounce * defBounce;
            float newBounce = defBounce*CrystalModifier.bounce.getFloatValue();
            GlStateManager.scale(CrystalModifier.scale.getValue(), CrystalModifier.scale.getValue(),CrystalModifier.scale.getValue());
            modelBase.render(entity, limbSwing, newSpin, newBounce, netHeadYaw, headPitch, scale);
        }else {
            modelBase.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }
}
