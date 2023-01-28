package international.astro.hack.hacks.misc;

import international.astro.hack.Hack;
import international.astro.hack.option.options.ODouble;
import international.astro.util.PlayerUtil;
import net.minecraft.block.BlockShulkerBox;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.List;

@Hack.Construct(name = "AntiReGear", description = "Stop them chink fags from re-gearing", category = Hack.Category.MISC)
public class AntiReGear extends Hack {


    public ODouble range = new ODouble("Range", 1,6,1,4);

    public AntiReGear(){
        addOption(range);
    }

    @SubscribeEvent
    public void onTick(TickEvent e){
        if(nullCheck()){return;}
        List<BlockPos> posList = PlayerUtil.getSphere(new BlockPos(Math.floor(mc.player.posX), Math.floor(mc.player.posY), Math.floor(mc.player.posZ)), range.getFloatValue(), 0, false, true, 0);
        for(BlockPos pos : posList){
            if (mc.world.getBlockState(pos).getBlock() instanceof BlockShulkerBox) {
                int tool = PlayerUtil.getBestAvailableToolSlot(mc.player.world.getBlockState(pos));
                mc.player.inventory.currentItem = tool;
                mc.player.swingArm(EnumHand.MAIN_HAND);
                mc.playerController.onPlayerDamageBlock(pos, PlayerUtil.getRayTraceFacing(pos));
            }
        }

    }
}
