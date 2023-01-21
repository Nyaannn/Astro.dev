package international.astro.events;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class DamageBlockEvent extends Event {
    public BlockPos pos;
    public EnumFacing facing;

    public DamageBlockEvent(final BlockPos pos, final EnumFacing facing) {
        this.pos = pos;
        this.facing = facing;
    }

    public final BlockPos getPos() {
        return this.pos;
    }

}