package international.astro.hack.hacks.combat;
import international.astro.Astro;
import international.astro.events.PacketReceiveEvent;
import international.astro.hack.Hack;
import international.astro.hack.option.options.OBoolean;
import international.astro.hack.option.options.ODouble;
import international.astro.mixins.accessor.ISPacketPlayerPosLook;
import international.astro.util.PlayerUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;
@Hack.Construct(name = "BedAura", description = "shit on bedfags", category = Hack.Category.COMBAT)
public class BedAura extends Hack {
    public static OBoolean Place = new OBoolean("Place", true);
    public static OBoolean Break = new OBoolean("Break", true);
    public static OBoolean NoRotate = new OBoolean("NoRotate", false);
    public static OBoolean InstaBreak = new OBoolean("InstaBreak", false);
    public static OBoolean PacketPlace = new OBoolean("PacketPlace", false);
    public static OBoolean PacketBreak = new OBoolean("PacketBreak", false);
    public static ODouble PlaceDelay = new ODouble("PlaceDelay", 0, 100, 1, 2);
    public static ODouble BreakDelay = new ODouble("BreakDelay", 0, 100, 1, 1);
    public ODouble Range = new ODouble("Range", 1, 20, 1, 20);
    public static ODouble MinHealth = new ODouble("MinHealth", 1, 35, 1, 9);
    public static OBoolean AutoSwitch = new OBoolean("AutoSwitch", true);
    public static ODouble BoundsScale = new ODouble("Bounds", 2, 15, 1, 8);

    int pdelay = 0;
    int bdelay = 0;
    double x = 0;
    double y = 0;
    double z = 0;

    private EntityPlayer target;

    public BedAura() {
        addOption(Place);
        addOption(Break);
        addOption(InstaBreak);
        addOption(NoRotate);
        addOption(AutoSwitch);
        addOption(PacketPlace);
        addOption(PacketBreak);
        addOption(PlaceDelay);
        addOption(BreakDelay);
        addOption(MinHealth);
        addOption(BoundsScale);
    }

    public static String[] config() {
        String[] fuck = {
                String.valueOf(Place.isEnabled()),
                String.valueOf(Break.isEnabled()),
                String.valueOf(InstaBreak.isEnabled()),
                String.valueOf(NoRotate.isEnabled()),
                String.valueOf(AutoSwitch.isEnabled()),
                String.valueOf(PacketPlace.isEnabled()),
                String.valueOf(PacketBreak.isEnabled()),
                String.valueOf(PlaceDelay.getValue()),
                String.valueOf(BreakDelay.getValue()),
                String.valueOf(MinHealth.getValue()),
                String.valueOf(BoundsScale.getValue()),
        };
        return fuck;
    }

    @Override
    public void onEnable() {
        target = null;
        pdelay = 0;
        bdelay = 0;
        x = 0;
        y = 0;
        z = 0;
    }

    @Override
    public void onDisable() {
        pdelay = 0;
        bdelay = 0;
        target = null;
        x = 0;
        y = 0;
        z = 0;
    }

    float yaw;
    float pitch;

    @SubscribeEvent
    public void onDis(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        this.disable();
    }

    @SubscribeEvent
    public void onReceive(PacketReceiveEvent e) {
        if (nullCheck()) {return;}
        if (NoRotate.isEnabled()) {
            Packet p = e.getPacket();
            if (p instanceof SPacketPlayerPosLook) {
                SPacketPlayerPosLook packet = (SPacketPlayerPosLook) e.getPacket();
                ((ISPacketPlayerPosLook)packet).setYaw(mc.player.rotationYaw);
                ((ISPacketPlayerPosLook) packet).setPitch(mc.player.rotationPitch);
            }
        }
    }

    public boolean canPlaceBed(BlockPos bp) {
        BlockPos north = bp.north();
        BlockPos east = bp.east();
        BlockPos south = bp.south();
        BlockPos west = bp.west();
        BlockPos northDown = bp.north().down();
        BlockPos eastDown = bp.east().down();
        BlockPos southDown = bp.south().down();
        BlockPos westDown = bp.west().down();
        if (mc.world.getBlockState(bp).getBlock() == Blocks.AIR && mc.world.getBlockState(bp.down()).getBlock() != Blocks.AIR
                && mc.world.getBlockState(north).getBlock() == Blocks.AIR && mc.world.getBlockState(northDown).getBlock() != Blocks.AIR) {
            return true;
        }
        if (mc.world.getBlockState(bp).getBlock() == Blocks.AIR && mc.world.getBlockState(bp.down()).getBlock() != Blocks.AIR
                && mc.world.getBlockState(east).getBlock() == Blocks.AIR && mc.world.getBlockState(eastDown).getBlock() != Blocks.AIR) {
            return true;
        }
        if (mc.world.getBlockState(bp).getBlock() == Blocks.AIR && mc.world.getBlockState(bp.down()).getBlock() != Blocks.AIR
                && mc.world.getBlockState(south).getBlock() == Blocks.AIR && mc.world.getBlockState(southDown).getBlock() != Blocks.AIR) {
            return true;
        }
        if (mc.world.getBlockState(bp).getBlock() == Blocks.AIR && mc.world.getBlockState(bp.down()).getBlock() != Blocks.AIR
                && mc.world.getBlockState(west).getBlock() == Blocks.AIR && mc.world.getBlockState(westDown).getBlock() != Blocks.AIR) {
            return true;
        }
        return false;
    }

    public void rotateTaDirection(EnumFacing a) {
        Astro.rotationManager.setRotations(a.getHorizontalAngle(), mc.player.rotationPitch);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (nullCheck()) {
            return;
        }
        yaw = mc.player.rotationYaw;
        pitch = mc.player.rotationPitch;
        if (mc.player.dimension == -1) {
            if (PlayerUtil.getSlot(Items.BED) != -1) {
                if (mc.player.getHealth() >= MinHealth.getIntValue()) {
                    if (AutoSwitch.isEnabled()) {
                        PlayerUtil.changeSlot(PlayerUtil.getSlot(Items.BED));
                    }
                    ;
                    List<EntityPlayer> closestPlayer = (List<EntityPlayer>) mc.world.playerEntities.stream().filter(player -> player.getDistance(mc.player) < Range.getIntValue() && player != mc.player && player.isEntityAlive()).collect(Collectors.toList());
                    closestPlayer.sort(Comparator.comparingDouble(player -> player.getDistanceSq(mc.player)));
                    if (!closestPlayer.isEmpty()) {
                        target = closestPlayer.get(0);
                        if (target != null) {
                            x = target.posX;
                            y = target.posY;
                            z = target.posZ;
                            BlockPos[] PosMap = {
                                    new BlockPos((int) x, (int) y, (int) z),
                                    new BlockPos((int) x + 1, (int) y + 1, (int) z),
                                    new BlockPos((int) x - 1, (int) y + 1, (int) z),
                                    new BlockPos((int) x, (int) y + 1, (int) z + 1),
                                    new BlockPos((int) x, (int) y + 1, (int) z - 1),
                                    new BlockPos((int) x + 1, (int) y, (int) z),
                                    new BlockPos((int) x - 1, (int) y, (int) z),
                                    new BlockPos((int) x, (int) y, (int) z + 1),
                                    new BlockPos((int) x, (int) y, (int) z - 1),
                                    new BlockPos((int) x + 2, (int) y + 1, (int) z),

                            };
                            if (Place.isEnabled()) {
                                if (pdelay >= PlaceDelay.getIntValue()) {
                                    if (canPlaceBed(PosMap[0])) {
                                        PlayerUtil.placeBlock(new BlockPos(PosMap[0]), EnumHand.MAIN_HAND, PacketPlace.isEnabled());
                                        if (InstaBreak.isEnabled()) {
                                            PlayerUtil.rightClickBlock(new BlockPos(PosMap[0]), mc.player.getLookVec(), EnumHand.MAIN_HAND, EnumFacing.DOWN, PacketBreak.isEnabled());
                                        }
                                    }
                                    if (canPlaceBed(PosMap[1])) {
                                        PlayerUtil.placeBlock(new BlockPos(PosMap[1]), EnumHand.MAIN_HAND, PacketPlace.isEnabled());
                                        if (InstaBreak.isEnabled()) {
                                            PlayerUtil.rightClickBlock(new BlockPos(PosMap[1]), mc.player.getLookVec(), EnumHand.MAIN_HAND, EnumFacing.DOWN, PacketBreak.isEnabled());
                                        }
                                    }
                                    if (canPlaceBed(PosMap[2])) {
                                        PlayerUtil.placeBlock(new BlockPos(PosMap[2]), EnumHand.MAIN_HAND, PacketPlace.isEnabled());
                                        if (InstaBreak.isEnabled()) {
                                            PlayerUtil.rightClickBlock(new BlockPos(PosMap[2]), mc.player.getLookVec(), EnumHand.MAIN_HAND, EnumFacing.DOWN, PacketBreak.isEnabled());
                                        }
                                    }
                                    if (canPlaceBed(PosMap[3])) {
                                        PlayerUtil.placeBlock(new BlockPos(PosMap[3]), EnumHand.MAIN_HAND, PacketPlace.isEnabled());
                                        if (InstaBreak.isEnabled()) {
                                            PlayerUtil.rightClickBlock(new BlockPos(PosMap[3]), mc.player.getLookVec(), EnumHand.MAIN_HAND, EnumFacing.DOWN, PacketBreak.isEnabled());
                                        }
                                    }
                                    if (canPlaceBed(PosMap[4])) {
                                        PlayerUtil.placeBlock(new BlockPos(PosMap[4]), EnumHand.MAIN_HAND, PacketPlace.isEnabled());
                                        if (InstaBreak.isEnabled()) {
                                            PlayerUtil.rightClickBlock(new BlockPos(PosMap[4]), mc.player.getLookVec(), EnumHand.MAIN_HAND, EnumFacing.DOWN, PacketBreak.isEnabled());
                                        }
                                    }
                                    if (canPlaceBed(PosMap[5])) {
                                        PlayerUtil.placeBlock(new BlockPos(PosMap[5]), EnumHand.MAIN_HAND, PacketPlace.isEnabled());
                                        if (InstaBreak.isEnabled()) {
                                            PlayerUtil.rightClickBlock(new BlockPos(PosMap[5]), mc.player.getLookVec(), EnumHand.MAIN_HAND, EnumFacing.DOWN, PacketBreak.isEnabled());
                                        }
                                    }
                                    if (canPlaceBed(PosMap[6])) {
                                        PlayerUtil.placeBlock(new BlockPos(PosMap[6]), EnumHand.MAIN_HAND, PacketPlace.isEnabled());
                                        if (InstaBreak.isEnabled()) {
                                            PlayerUtil.rightClickBlock(new BlockPos(PosMap[6]), mc.player.getLookVec(), EnumHand.MAIN_HAND, EnumFacing.DOWN, PacketBreak.isEnabled());
                                        }
                                    }
                                    if (canPlaceBed(PosMap[7])) {
                                        PlayerUtil.placeBlock(new BlockPos(PosMap[7]), EnumHand.MAIN_HAND, PacketPlace.isEnabled());
                                        if (InstaBreak.isEnabled()) {
                                            PlayerUtil.rightClickBlock(new BlockPos(PosMap[7]), mc.player.getLookVec(), EnumHand.MAIN_HAND, EnumFacing.DOWN, PacketBreak.isEnabled());
                                        }
                                    }
                                    if (canPlaceBed(PosMap[8])) {
                                        PlayerUtil.placeBlock(new BlockPos(PosMap[8]), EnumHand.MAIN_HAND, PacketPlace.isEnabled());
                                        if (InstaBreak.isEnabled()) {
                                            PlayerUtil.rightClickBlock(new BlockPos(PosMap[8]), mc.player.getLookVec(), EnumHand.MAIN_HAND, EnumFacing.DOWN, PacketBreak.isEnabled());
                                        }
                                    }
                                    if (canPlaceBed(PosMap[9])) {
                                        PlayerUtil.placeBlock(new BlockPos(PosMap[9]), EnumHand.MAIN_HAND, PacketPlace.isEnabled());
                                        if (InstaBreak.isEnabled()) {
                                            PlayerUtil.rightClickBlock(new BlockPos(PosMap[9]), mc.player.getLookVec(), EnumHand.MAIN_HAND, EnumFacing.DOWN, PacketBreak.isEnabled());
                                        }
                                    }
                                    pdelay = 0;
                                }
                            }
                            if (Break.isEnabled()) {
                                if (!InstaBreak.isEnabled()) {
                                    if (bdelay >= BreakDelay.getIntValue()) {
                                        for (TileEntity bed : mc.world.loadedTileEntityList) {
                                            if (bed.getBlockType() == Blocks.BED) {
                                                if (bed.getDistanceSq(x, y, z) <= BoundsScale.getValue()) {
                                                    PlayerUtil.rightClickBlock(new BlockPos(bed.getPos()), mc.player.getLookVec(), EnumHand.MAIN_HAND, EnumFacing.DOWN, PacketBreak.isEnabled());
                                                }
                                            }
                                        }
                                        bdelay = 0;
                                    }
                                }
                            }
                            pdelay++;
                            bdelay++;
                        }
                    }
                } else {
                    Astro.sendMsg("You need to have higher health to use bed aura");
                    this.disable();
                }

            } else {
                Astro.sendMsg("You need to have beds to use bed aura");
                this.disable();
            }
        } else {
            Astro.sendMsg("You need to be in the nether to use bed aura");
            this.disable();
        }

    }
}