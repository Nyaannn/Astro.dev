package international.astro.hack.hacks.combat;

import international.astro.hack.Hack;
import international.astro.hack.option.options.OBoolean;
import international.astro.hack.option.options.OList;
import international.astro.util.PlayerUtil;
import international.astro.util.TimerUtil;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "Burrow", description = "self fill", category = Hack.Category.COMBAT)
public class Burrow extends Hack {
    public Burrow() {
        addOption(sneak);
        addOption(mode);
        addOption(aSwitch);
    }
    OBoolean aSwitch = new OBoolean("AutoSwitch",true);
    OBoolean sneak = new OBoolean("Sneak",true);
    OList mode = new OList("Mode", "5b5t", "5b5t", "Normal");

    int oldSlot;
    @Override
    public void onEnable() {
        if(nullCheck()){return;}
        oldSlot = mc.player.inventory.currentItem;
        if(mc.player.onGround) {
            TimerUtil timer = new TimerUtil();
            if(aSwitch.isEnabled()){
                int blockSlot = PlayerUtil.getSlot(Item.getItemFromBlock(Blocks.OBSIDIAN));
                mc.player.connection.sendPacket(new CPacketHeldItemChange(blockSlot));
            }
            if(timer.sleep(100L)) {
                if(mode.isMode("5b5t")|| mode.isMode("Normal")) {
                    BlockPos pos = PlayerUtil.getPosFloored(mc.player);
                    mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.41999998688698D, mc.player.posZ, true));
                    mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.7531999805211997D, mc.player.posZ, true));
                    mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 1.00133597911214D, mc.player.posZ, true));
                    mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 1.16610926093821D, mc.player.posZ, true));
                    PlayerUtil.placeBlock(pos, EnumHand.MAIN_HAND, true);
                    mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 1.5f, mc.player.posZ, false));
                    if (sneak.isEnabled() && !mc.player.isSneaking()) {
                        mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.START_SNEAKING));
                        mc.player.setSneaking(true);
                        mc.playerController.updateController();
                        mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                        mc.player.setSneaking(false);
                        mc.playerController.updateController();
                    }
                }
                timer.reset();
            }
            toggle();
        }
    }

    @Override
    public void onDisable() {
        mc.player.connection.sendPacket(new CPacketHeldItemChange(oldSlot));
    }

    @SubscribeEvent
    public void onUpdate(final TickEvent.ClientTickEvent event){
        if(nullCheck()){return;}
        if(mode.isMode("5b5t")) {
            mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY - 0.02523D, mc.player.posZ, true));
        }
    }
}
