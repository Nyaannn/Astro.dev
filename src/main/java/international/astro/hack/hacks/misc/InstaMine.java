package international.astro.hack.hacks.misc;

import international.astro.Astro;
import international.astro.events.DamageBlockEvent;
import international.astro.events.PacketSendEvent;
import international.astro.hack.Hack;
import international.astro.util.PlayerUtil;
import international.astro.util.RenderUtils;
import international.astro.util.TimerUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

@Hack.Construct(name = "InstaMine", description = "best instant mine", category = Hack.Category.MISC)
public class InstaMine extends Hack {
    double manxi;
    Block block;
    TimerUtil breakSuccess;
    boolean empty;
    TimerUtil Rendertimer;
    EnumFacing facing;
    List<Block> blacklist;
    BlockPos breakPos;
    BlockPos breakPos2;
    boolean cancelStart;

    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent renderWorldLastEvent) {
        if (cancelStart && !nullCheck()) {
            if (blacklist.contains(mc.world.getBlockState(breakPos).getBlock())) {
                empty = true;
            }
            if ((breakPos != null || (mc.world.getBlockState(breakPos).getBlock() == Blocks.AIR)) && mc.player != null && mc.player.getDistanceSq(breakPos) > 65536) {
                breakPos = null;
                breakPos2 = null;
                cancelStart = false;
                return;
            }
            float n = mc.world.getBlockState(breakPos).getBlockHardness(mc.world, breakPos) / 5.0f;
            if (mc.world.getBlockState(breakPos).getBlock() == Blocks.OBSIDIAN) {
                n = 11.0f;
            }
            if (Rendertimer.passedMs((int)n)) {
                if (manxi <= 10.0) {
                    manxi += 0.11;
                }
                Rendertimer.reset();
            }
            RenderUtils.drawBoxESP(breakPos, Astro.colorManager.getColor(), 1,true, true, 100);
        }
    }

    public InstaMine(){
        breakSuccess = new TimerUtil();
        Rendertimer = new TimerUtil();
        blacklist = Arrays.asList(
                Blocks.AIR,
                Blocks.FLOWING_LAVA,
                Blocks.LAVA,
                Blocks.FLOWING_WATER,
                Blocks.WATER,
                Blocks.BEDROCK,
                Blocks.END_PORTAL_FRAME,
                Blocks.PORTAL);
        cancelStart = false;
        empty = false;
    }

    @SubscribeEvent
    public void onTick(TickEvent e) {
        if (nullCheck()) {return;}
        if (mc.player.capabilities.isCreativeMode) {return;}
        if (!cancelStart) {return;}
        if (breakPos != null && mc.player != null && mc.player.getDistanceSq(breakPos) > 65536) {
            breakPos = null;
            breakPos2 = null;
            cancelStart = false;
            return;
        }
        if (blacklist.contains(mc.world.getBlockState(breakPos).getBlock())) {
            return;
        }
        if(breakPos != null) {
            final float getBlockHardness = mc.world.getBlockState(breakPos).getBlockHardness(mc.world, breakPos);
            final int currentItem2 = mc.player.inventory.currentItem;
            if (!breakSuccess.passedMs((int) getBlockHardness)) {
                return;
            }
            try {
                block = mc.world.getBlockState(breakPos).getBlock();
            } catch (Exception ex) {
            }
            final int bestAvailableToolSlot = PlayerUtil.getBestAvailableToolSlot(block.getBlockState().getBaseState());
            if (mc.player.inventory.currentItem != bestAvailableToolSlot && bestAvailableToolSlot != -1) {
                mc.player.inventory.currentItem = bestAvailableToolSlot;
                mc.playerController.updateController();
                mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, breakPos, facing));
                mc.player.inventory.currentItem = currentItem2;
                mc.playerController.updateController();
                return;
            }
        }
     }

    @SubscribeEvent
    public void onPacketSend(PacketSendEvent e) {
        if (nullCheck()) {return;}
        if (mc.player.capabilities.isCreativeMode) {return;}
        if (e.getPacket() instanceof CPacketPlayerDigging && ((CPacketPlayerDigging)e.getPacket()).getAction() == CPacketPlayerDigging.Action.START_DESTROY_BLOCK) {
            e.setCanceled(cancelStart);
        }
    }


    public boolean canBreak(final BlockPos blockPos) {
        IBlockState getBlockState = mc.world.getBlockState(blockPos);
        return getBlockState.getBlock().getBlockHardness(getBlockState, mc.world, blockPos) != -1.0f;
    }

    @SubscribeEvent
    public void onBlocDamagekEvent(DamageBlockEvent damageBlockEvent) {
        if (nullCheck() || mc.player.capabilities.isCreativeMode) {return;}
        if (breakPos != null && breakPos.getX() == damageBlockEvent.getPos().getX() && breakPos.getY() == damageBlockEvent.getPos().getY() && breakPos.getZ() == damageBlockEvent.getPos().getZ()) {
            return;
        }
        if (canBreak(damageBlockEvent.pos)) {
            manxi = 0.0;
            breakPos2 = breakPos;
            empty = false;
            cancelStart = false;
            breakPos = damageBlockEvent.pos;
            breakSuccess.reset();
            facing = damageBlockEvent.facing;
            if (breakPos != null) {
                mc.player.swingArm(EnumHand.MAIN_HAND);
                mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, breakPos, facing));
                cancelStart = true;
                mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, breakPos, facing));
                damageBlockEvent.setCanceled(true);
            }
        }
    }
}