package international.astro.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;

public class PlayerUtil {

    public static Minecraft mc  = Minecraft.getMinecraft();

    public static void changeSlot(int slot) {
        if (slot == -1) {return;}
        mc.player.inventory.currentItem = slot;
    }

    public static BlockPos getPosFloored(EntityPlayer p){
        return new BlockPos(Math.floor(p.posX), Math.floor(p.posY), Math.floor(p.posZ));
    }

    public static int getSlot(Item items) {
        int slot = -1;
        for (int i = 0; i < 9; i++) {
            Item item = mc.player.inventory.getStackInSlot(i).getItem();
            if (item == items) {
                slot = i;
                break;
            }
        }
        return slot;
    }

    public static boolean placeBlock(BlockPos pos, EnumHand hand, boolean packet) {
        EnumFacing side = getFirstFacing(pos);
        if (side == null) return false;
        BlockPos neighbour = pos.offset(side);
        EnumFacing opposite = side.getOpposite();
        Vec3d hitVec = new Vec3d(neighbour).add(0.5, 0.5, 0.5).add(new Vec3d(opposite.getDirectionVec()).scale(0.5));
        rightClickBlock(neighbour, hitVec, hand, opposite, packet);
        mc.player.swingArm(EnumHand.MAIN_HAND);
        return true;
    }

    public static EnumFacing getFirstFacing(BlockPos pos) {
        for (EnumFacing facing : getPossibleSides(pos)) {
            return facing;
        }
        return null;
    }

    public static List<EnumFacing> getPossibleSides(BlockPos pos) {
        List<EnumFacing> facings = new ArrayList<>();
        for (EnumFacing side : EnumFacing.values()) {
            BlockPos neighbour = pos.offset(side);
            if (mc.world.getBlockState(neighbour) == null) return facings;
            if (mc.world.getBlockState(neighbour).getBlock() == null) return facings;
            if (mc.world.getBlockState(neighbour).getBlock().canCollideCheck(mc.world.getBlockState(neighbour), false)) {
                IBlockState blockState = mc.world.getBlockState(neighbour);
                if (!blockState.getMaterial().isReplaceable()) {
                    facings.add(side);
                }
            }
        }
        return facings;
    }

    public static void rightClickBlock(BlockPos pos, Vec3d vec, EnumHand hand, EnumFacing direction, boolean packet) {
        if (packet) {
            float f = (float) (vec.x - (double) pos.getX());
            float f1 = (float) (vec.y - (double) pos.getY());
            float f2 = (float) (vec.z - (double) pos.getZ());
            mc.player.connection.sendPacket(new CPacketPlayerTryUseItemOnBlock(pos, direction, hand, f, f1, f2));
        } else {
            mc.playerController.processRightClickBlock(mc.player, mc.world, pos, direction, vec, hand);
        }
    }

    public static float[] getRotations(Entity e) {
        double deltaX = e.posX + (e.posX - e.lastTickPosX) - mc.player.posX,
                deltaY = e.posY - 3.5 + e.getEyeHeight() - mc.player.posY + mc.player.getEyeHeight(),
                deltaZ = e.posZ + (e.posZ - e.lastTickPosZ) - mc.player.posZ,
                distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaZ, 2));

        float yaw = (float) Math.toDegrees(-Math.atan(deltaX / deltaZ)),
                pitch = (float) -Math.toDegrees(Math.atan(deltaY / distance));

        if (deltaX < 0 && deltaZ < 0) {
            yaw = (float) (90 + Math.toDegrees(Math.atan(deltaZ / deltaX)));
        } else if (deltaX > 0 && deltaZ < 0) {
            yaw = (float) (-90 + Math.toDegrees(Math.atan(deltaZ / deltaX)));
        }

        return new float[] {yaw, pitch};
    }
}
