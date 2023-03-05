package international.astro.hack.hacks.combat;

import international.astro.hack.Hack;
import international.astro.util.PlayerUtil;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.input.Keyboard;

@Hack.Construct(name = "SelfWeb", description = "web urself", category = Hack.Category.COMBAT)
public class SelfWeb extends Hack {

    @Override
    public void onEnable() {
        if(PlayerUtil.getSlot(Item.getItemFromBlock(Blocks.WEB)) != -1) {
            int prevSlot = mc.player.inventory.currentItem;
            PlayerUtil.changeSlot(PlayerUtil.getSlot(Item.getItemFromBlock(Blocks.WEB)));
            PlayerUtil.placeBlock(new BlockPos(PlayerUtil.getPosFloored(mc.player)), EnumHand.MAIN_HAND, false);
            mc.player.connection.sendPacket(new CPacketHeldItemChange(prevSlot));

        }
        setEnabled(false);
        super.onEnable();

    }


}
