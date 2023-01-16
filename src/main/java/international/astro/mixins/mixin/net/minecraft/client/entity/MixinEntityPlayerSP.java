package international.astro.mixins.mixin.net.minecraft.client.entity;

import com.mojang.authlib.GameProfile;
import international.astro.Astro;
import international.astro.events.PostMotionEvent;
import international.astro.events.PreMotionEvent;
import international.astro.hack.hacks.misc.ChatPlus;
import international.astro.mixins.accessor.IEntityPlayerSP;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntityPlayerSP.class})
public abstract class MixinEntityPlayerSP extends AbstractClientPlayer implements IEntityPlayerSP {

    @Override
    @Accessor
    public abstract void setLastReportedPosY(double d);

    public MixinEntityPlayerSP(World p_i45074_1_, GameProfile p_i45074_2_) {
        super(p_i45074_1_, p_i45074_2_);
    }

    @Redirect(method = { "onLivingUpdate" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;closeScreen()V"))
    public void closeScreen(final EntityPlayerSP entityPlayerSP) {
        if (Astro.hackManager.getHack("ChatPlus").isEnabled() && ChatPlus.getInstance.portalChat.isEnabled()) {
            return;
        }
    }

    @Redirect(method = { "onLivingUpdate" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;displayGuiScreen(Lnet/minecraft/client/gui/GuiScreen;)V"))
    public void closeScreen(final Minecraft minecraft, final GuiScreen screen) {
        if (Astro.hackManager.getHack("ChatPlus").isEnabled() && ChatPlus.getInstance.portalChat.isEnabled()) {
            return;
        }
    }

    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("HEAD") }, cancellable = true)
    protected void onUpdateWalkingPlayer_Head(final CallbackInfo callbackInfo) {
        PreMotionEvent motionEvent = new PreMotionEvent(this.posX, this.getEntityBoundingBox().minY, this.posZ, this.rotationYaw, this.rotationPitch, this.onGround);
        MinecraftForge.EVENT_BUS.register(motionEvent);
        if (motionEvent.isCanceled()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("RETURN") })
    protected void onUpdateWalkingPlayer_Return(final CallbackInfo callbackInfo) {
        PostMotionEvent postMotionUpdate = new PostMotionEvent();
        postMotionUpdate.setCanceled(postMotionUpdate.isCanceled());
        MinecraftForge.EVENT_BUS.register(postMotionUpdate);
    }
}
