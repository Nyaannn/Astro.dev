package international.astro.hack.hacks.misc;

import international.astro.hack.Hack;
import international.astro.hack.option.options.OBoolean;
import international.astro.hack.option.options.ODouble;
import international.astro.util.PlayerUtil;
import net.minecraft.block.BlockShulkerBox;
<<<<<<< HEAD
import net.minecraft.init.Blocks;
import net.minecraft.init.Blocks.*;
import net.minecraft.init.Items;
=======
>>>>>>> 54c4a826ad0df3f3e9b646312425ccd46dd7d76c
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.List;

@Hack.Construct(name = "AntiReGear", description = "Stop them chink fags from re-gearing", category = Hack.Category.MISC)
public class AntiReGear extends Hack {


    public ODouble range = new ODouble("Range", 1,6,1,4);
    public OBoolean silent = new OBoolean("SilentSwitch", true);

    public AntiReGear(){
        addOption(range);
        addOption(silent);
    }

    @SubscribeEvent
    public void onTick(TickEvent e){
        if(nullCheck()){return;}
        List<BlockPos> bubble = PlayerUtil.getSphere(new BlockPos(Math.floor(mc.player.posX), Math.floor(mc.player.posY), Math.floor(mc.player.posZ)), range.getFloatValue(), 0, false, true, 0);
        List<BlockPos> shulkList = new ArrayList<>();
        for(BlockPos pos : bubble){
            if (mc.world.getBlockState(pos).getBlock() instanceof BlockShulkerBox) {
                shulkList.add(pos);
            }
        }
        for(BlockPos shulk : shulkList){
            int tool = PlayerUtil.getBestAvailableToolSlot(mc.player.world.getBlockState(shulk));
            if(silent.isEnabled()){
                mc.player.connection.sendPacket(new CPacketHeldItemChange(tool));
                mc.playerController.updateController();
            }else {
                mc.player.connection.sendPacket(new CPacketHeldItemChange(tool));
                mc.player.inventory.currentItem = tool;
                mc.playerController.updateController();
            }
            mc.player.swingArm(EnumHand.MAIN_HAND);
            mc.playerController.onPlayerDamageBlock(shulk, PlayerUtil.getRayTraceFacing(shulk));

        }


    }
}
