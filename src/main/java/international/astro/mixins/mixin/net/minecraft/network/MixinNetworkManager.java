package international.astro.mixins.mixin.net.minecraft.network;

import international.astro.Astro;
import international.astro.events.PacketReceiveEvent;
import international.astro.events.PacketSendEvent;
import international.astro.mixins.accessor.ICPacketPlayer;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({NetworkManager.class})
public class MixinNetworkManager {

    @Inject(method = "sendPacket(Lnet/minecraft/network/Packet;)V", at = @At("HEAD"), cancellable = true)
    private void onPacketSend(Packet<?> packet, CallbackInfo ci) {
        PacketSendEvent event = new PacketSendEvent(packet);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled()) ci.cancel();
        if(event.getPacket() instanceof CPacketPlayer.Rotation || event.getPacket() instanceof CPacketPlayer.PositionRotation) {
            if (Astro.rotationManager.isRotating()) {
                CPacketPlayer p = (CPacketPlayer) event.getPacket();
                ((ICPacketPlayer)p).setYaw(Astro.rotationManager.getYaw());
                ((ICPacketPlayer)p).setPitch(Astro.rotationManager.getPitch());
            }
        }}

    @Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
    public void channelRead0(ChannelHandlerContext p_channelRead0_1_, Packet<?> p_channelRead0_2_, final CallbackInfo ci) {
        PacketReceiveEvent event = new PacketReceiveEvent(p_channelRead0_2_);
        MinecraftForge.EVENT_BUS.post(event);
        if(event.isCanceled()) ci.cancel();
    }


}
