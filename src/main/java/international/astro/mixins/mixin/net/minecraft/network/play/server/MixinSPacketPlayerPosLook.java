package international.astro.mixins.mixin.net.minecraft.network.play.server;

import international.astro.mixins.accessor.ISPacketPlayerPosLook;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({SPacketPlayerPosLook.class})
public abstract class MixinSPacketPlayerPosLook implements ISPacketPlayerPosLook {

    @Override
    @Accessor
    public abstract void setYaw(float f);

    @Override
    @Accessor
    public abstract void setPitch(float f);
}
