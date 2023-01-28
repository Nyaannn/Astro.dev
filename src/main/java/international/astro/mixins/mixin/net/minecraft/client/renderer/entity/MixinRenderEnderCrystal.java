package international.astro.mixins.mixin.net.minecraft.client.renderer.entity;

import international.astro.Astro;
import international.astro.hack.hacks.render.CrystalModifier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderEnderCrystal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

//WIP
@Mixin({RenderEnderCrystal.class})
public class MixinRenderEnderCrystal {

//    protected Minecraft mc = Minecraft.getMinecraft();
//
//    @Redirect(method = "doRender", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V"))
//    public void doRender(ModelBase modelBase, Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
//        if(Astro.hackManager.getHack("CrystalModifier").isEnabled()) {
//            float defSpeed = ((EntityEnderCrystal)entity).innerRotation + mc.getRenderPartialTicks();
//            float r = CrystalModifier.speed.getFloatValue()*defSpeed;
//            GlStateManager.scale(CrystalModifier.scale.getFloatValue(), CrystalModifier.scale.getFloatValue(), CrystalModifier.scale.getFloatValue());
//            modelBase.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
//        }else {
//            modelBase.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
//        }
//    }
}
