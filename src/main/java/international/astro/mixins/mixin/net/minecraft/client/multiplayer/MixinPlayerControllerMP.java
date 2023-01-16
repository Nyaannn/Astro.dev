package international.astro.mixins.mixin.net.minecraft.client.multiplayer;

import international.astro.events.AttackEvent;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({PlayerControllerMP.class})
public class MixinPlayerControllerMP {

    @Inject(method = "attackEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;syncCurrentPlayItem()V"), cancellable = true)
    private void attackEntity(EntityPlayer entityPlayer, Entity target, CallbackInfo ci) {
        if (target == null) {return;}
        AttackEvent event = new AttackEvent(target);
        MinecraftForge.EVENT_BUS.register(event);
        if (event.isCanceled()) {ci.cancel();}
    }
}
