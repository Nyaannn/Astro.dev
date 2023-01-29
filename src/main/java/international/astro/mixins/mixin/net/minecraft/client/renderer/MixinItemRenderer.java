package international.astro.mixins.mixin.net.minecraft.client.renderer;

import international.astro.Astro;
import international.astro.hack.hacks.render.Animations;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({ItemRenderer.class})
public abstract class MixinItemRenderer {

    protected Minecraft mc = Minecraft.getMinecraft();
    @Shadow
    public ItemStack itemStackOffHand;
    @Shadow
    protected abstract void renderMapFirstPerson(float p_187463_1_, float p_187463_2_, float p_187463_3_);

    @Shadow
    protected abstract void renderMapFirstPersonSide(float p_renderMapFirstPersonSide_1_, EnumHandSide p_renderMapFirstPersonSide_2_, float p_renderMapFirstPersonSide_3_, ItemStack p_renderMapFirstPersonSide_4_);

    @Shadow
    protected abstract void renderArmFirstPerson(float p_187456_1_, float p_187456_2_, EnumHandSide p_187456_3_);

    @Shadow
    protected abstract void transformSideFirstPerson(EnumHandSide p_transformSideFirstPerson_1_, float p_transformSideFirstPerson_2_);

    @Shadow
    protected abstract void transformEatFirstPerson(float p_transformEatFirstPerson_1_, EnumHandSide p_transformEatFirstPerson_2_, ItemStack p_transformEatFirstPerson_3_);

    @Shadow
    public abstract void renderItemSide(EntityLivingBase p_renderItemSide_1_, ItemStack p_renderItemSide_2_, ItemCameraTransforms.TransformType p_renderItemSide_3_, boolean p_renderItemSide_4_);

    /**
     * @author Logging4J
     * @reason Animations
     */
    @Overwrite
    public void renderItemInFirstPerson(AbstractClientPlayer p_renderItemInFirstPerson_1_, float p_renderItemInFirstPerson_2_, float p_renderItemInFirstPerson_3_, EnumHand p_renderItemInFirstPerson_4_, float p_renderItemInFirstPerson_5_, ItemStack p_renderItemInFirstPerson_6_, float p_renderItemInFirstPerson_7_) {
        boolean flag = p_renderItemInFirstPerson_4_ == EnumHand.MAIN_HAND;
        EnumHandSide enumhandside = flag ? p_renderItemInFirstPerson_1_.getPrimaryHand() : p_renderItemInFirstPerson_1_.getPrimaryHand().opposite();
        GlStateManager.pushMatrix();
        if (p_renderItemInFirstPerson_6_.isEmpty()) {
            if (flag && !p_renderItemInFirstPerson_1_.isInvisible()) {
                renderArmFirstPerson(p_renderItemInFirstPerson_7_, p_renderItemInFirstPerson_5_, enumhandside);
            }
        } else if (p_renderItemInFirstPerson_6_.getItem() instanceof ItemMap) {
            if (flag && itemStackOffHand.isEmpty()) {
                renderMapFirstPerson(p_renderItemInFirstPerson_3_, p_renderItemInFirstPerson_7_, p_renderItemInFirstPerson_5_);
            } else {
                renderMapFirstPersonSide(p_renderItemInFirstPerson_7_, enumhandside, p_renderItemInFirstPerson_5_, p_renderItemInFirstPerson_6_);
            }
        } else {
            boolean flag1 = enumhandside == EnumHandSide.RIGHT;
            float f5;
            float f6;
            if (p_renderItemInFirstPerson_1_.isHandActive() && p_renderItemInFirstPerson_1_.getItemInUseCount() > 0 && p_renderItemInFirstPerson_1_.getActiveHand() == p_renderItemInFirstPerson_4_) {
                int j = flag1 ? 1 : -1;
                switch (p_renderItemInFirstPerson_6_.getItemUseAction()) {
                    case NONE:
                        transformSideFirstPerson(enumhandside, p_renderItemInFirstPerson_7_);
                        break;
                    case EAT:
                    case DRINK:
                        transformEatFirstPerson(p_renderItemInFirstPerson_2_, enumhandside, p_renderItemInFirstPerson_6_);
                        transformSideFirstPerson(enumhandside, p_renderItemInFirstPerson_7_);
                        break;
                    case BLOCK:
                        transformSideFirstPerson(enumhandside, p_renderItemInFirstPerson_7_);
                        break;
                    case BOW:
                        transformSideFirstPerson(enumhandside, p_renderItemInFirstPerson_7_);
                        GlStateManager.translate((float)j * -0.2785682F, 0.18344387F, 0.15731531F);
                        GlStateManager.rotate(-13.935F, 1.0F, 0.0F, 0.0F);
                        GlStateManager.rotate((float)j * 35.3F, 0.0F, 1.0F, 0.0F);
                        GlStateManager.rotate((float)j * -9.785F, 0.0F, 0.0F, 1.0F);
                        f5 = (float)p_renderItemInFirstPerson_6_.getMaxItemUseDuration() - ((float)mc.player.getItemInUseCount() - p_renderItemInFirstPerson_2_ + 1.0F);
                        f6 = f5 / 20.0F;
                        f6 = (f6 * f6 + f6 * 2.0F) / 3.0F;
                        if (f6 > 1.0F) {
                            f6 = 1.0F;
                        }

                        if (f6 > 0.1F) {
                            float f7 = MathHelper.sin((f5 - 0.1F) * 1.3F);
                            float f3 = f6 - 0.1F;
                            float f4 = f7 * f3;
                            GlStateManager.translate(f4 * 0.0F, f4 * 0.004F, f4 * 0.0F);
                        }

                        GlStateManager.translate(f6 * 0.0F, f6 * 0.0F, f6 * 0.04F);
                        GlStateManager.scale(1.0F, 1.0F, 1.0F + f6 * 0.2F);
                        GlStateManager.rotate((float)j * 45.0F, 0.0F, -1.0F, 0.0F);
                }
            } else {
                float f = -0.4F * MathHelper.sin(MathHelper.sqrt(p_renderItemInFirstPerson_5_) * 3.1415927F);
                f5 = 0.2F * MathHelper.sin(MathHelper.sqrt(p_renderItemInFirstPerson_5_) * 6.2831855F);
                f6 = -0.2F * MathHelper.sin(p_renderItemInFirstPerson_5_ * 3.1415927F);
                int i = flag1 ? 1 : -1;
                float swingprogress = mc.player.getSwingProgress(mc.getRenderPartialTicks());;
                if(Astro.hackManager.getHack("Animations").isEnabled()){
                    if(enumhandside != EnumHandSide.LEFT) {
                        if (Animations.getInstance.mode.isMode("Dortware")) {
                            GlStateManager.translate(0.56F, -0.5F, -0.71999997F);
                            GlStateManager.translate(0.0F, 0.0F, 0.0F);
                            GlStateManager.rotate(45.0f, 0.0f, 1.0f, 0.0f);
                            float var11 = MathHelper.sin(swingprogress * swingprogress * (float) Math.PI);
                            float var12 = MathHelper.sin(MathHelper.sqrt(swingprogress) * (float) Math.PI);
                            GlStateManager.rotate(var11 * 0.0F, 0.0F, 1.0F, 0.0F);
                            GlStateManager.rotate(var12 * 0.0F, 0.0F, 0.0f, 1.0F);
                            GlStateManager.rotate(var12 * -90.0F, 1.0F, 0.0F, 0.0F);
                        }
                    }else {
                        GlStateManager.translate((float) i * f, f5, f6);
                        transformSideFirstPerson(enumhandside, p_renderItemInFirstPerson_7_);
                        transformFirstPerson(enumhandside, p_renderItemInFirstPerson_5_);
                    }
                }else {
                    GlStateManager.translate((float) i * f, f5, f6);
                    transformSideFirstPerson(enumhandside, p_renderItemInFirstPerson_7_);
                    transformFirstPerson(enumhandside, p_renderItemInFirstPerson_5_);
                }

            }

            renderItemSide(p_renderItemInFirstPerson_1_, p_renderItemInFirstPerson_6_, flag1 ? ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND : ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND, !flag1);
        }

        GlStateManager.popMatrix();
    }

    /**
     * @author Logging4J
     * @reason animations
     */

    @Overwrite
    private void transformFirstPerson(EnumHandSide p_187453_1_, float p_187453_2_) {
        float angle = System.currentTimeMillis() / 3L % 360L;
        int i = p_187453_1_ == EnumHandSide.RIGHT ? 1 : -1;
        float f = MathHelper.sin(p_187453_2_ * p_187453_2_ * (float) Math.PI);
        GlStateManager.rotate(i * (45.0F + f * -20.0F), 0.0F, 1.0F, 0.0F);
        float f1 = MathHelper.sin(MathHelper.sqrt(p_187453_2_) * (float) Math.PI);
        GlStateManager.rotate(i * f1 * -20.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(f1 * -80.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(i * -45.0f, 0.0F, 1.0F, 0.0F);
    }

}
