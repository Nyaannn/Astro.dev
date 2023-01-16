package international.astro.mixins.mixin.net.minecraft.network.play.client;

import international.astro.mixins.accessor.ICPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({CPacketPlayer.class})
public abstract class MixinCPacketPlayer implements ICPacketPlayer {

    @Override
    @Accessor("x")
    public abstract void setX(double d);

    @Override
    @Accessor("y")
    public abstract void setY(double d);

    @Override
    @Accessor("z")
    public abstract void setZ(double d);

    @Override
    @Accessor
    public abstract void setYaw(float f);

    @Override
    @Accessor
    public abstract void setPitch(float f);

    @Override
    @Accessor
    public abstract void setOnGround(boolean b);

    @Override
    @Accessor
    public abstract void setMoving(boolean b);


}
