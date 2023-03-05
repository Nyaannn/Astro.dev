package international.astro.hack.hacks.combat;
import international.astro.hack.Hack;
import international.astro.hack.option.options.OBoolean;
import international.astro.hack.option.options.ODouble;
import international.astro.util.PlayerUtil;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
@Hack.Construct(name = "HoleFill", description = "fill holes", category = Hack.Category.COMBAT)
public class HoleFill extends Hack {
    public static ODouble Range = new ODouble("Range", 2, 10, 1, 5);
    public static OBoolean PacketPlace = new OBoolean("PacketPlace", false);

    public HoleFill() {
        addOption(Range);
        addOption(PacketPlace);
    }

    public boolean FemboyHoleIsFillable(BlockPos p) {
        return mc.world.getBlockState(p).getBlock() == Blocks.AIR &&
                mc.world.getBlockState(p.north()).getBlock() != Blocks.AIR &&
                mc.world.getBlockState(p.east()).getBlock() != Blocks.AIR &&
                mc.world.getBlockState(p.south()).getBlock() != Blocks.AIR &&
                mc.world.getBlockState(p.west()).getBlock() != Blocks.AIR &&
                p != mc.player.getPosition();
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent e) {
        if (nullCheck()) {
            return;
        }
        for (TileEntity cock : mc.world.loadedTileEntityList) {
            if (cock.getBlockType() == Blocks.AIR) {
                if (cock.getDistanceSq(mc.player.posX, mc.player.posY, mc.player.posZ) <= Range.getIntValue()) {
                    if (FemboyHoleIsFillable(cock.getPos())) {
                        if (PlayerUtil.getSlot(Item.getItemFromBlock(Blocks.OBSIDIAN)) != -1) {
                            PlayerUtil.changeSlot(PlayerUtil.getSlot(Item.getItemFromBlock(Blocks.OBSIDIAN)));
                            PlayerUtil.placeBlock(cock.getPos(), EnumHand.MAIN_HAND, PacketPlace.isEnabled());
                        }
                    } else {
                        this.disable();
                    }
                }
            }
        }
    }
}
