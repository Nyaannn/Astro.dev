package international.astro.hack.hacks.combat;
import international.astro.Astro;
import international.astro.hack.Hack;
import international.astro.hack.option.options.OBoolean;
import international.astro.hack.option.options.ODouble;
import international.astro.util.PlayerUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
@Hack.Construct(name = "Surround", description = "Surround", category = Hack.Category.COMBAT)
public class Surround extends Hack {
    ODouble Delay = new ODouble("Delay", 1, 20, 1, 1);
    OBoolean SelfWeb = new OBoolean("SelfWeb", false);
    OBoolean CenterSelf = new OBoolean("Center", false);
    OBoolean AutoDisable = new OBoolean("AutoDisable", false);
    OBoolean PacketPLace = new OBoolean("PacketPlace", false);
    int del = 0;
    private EntityPlayer target;
    public Surround() {
        addOption(Delay);
        addOption(CenterSelf);
        addOption(SelfWeb);
        addOption(AutoDisable);
        addOption(PacketPLace);
    }
    public static BlockPos getPosByFloor(){
        return new BlockPos(Math.floor(mc.player.posX), Math.floor(mc.player.posY), Math.floor(mc.player.posZ));
    }
    @Override
    public void onEnable() {
        del = 0;
    }
    @Override
    public void onDisable() {
        del = 0;
    }
    @SubscribeEvent
    public void onDis(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        this.disable();
    }
    public void Center(){
        BlockPos pos = getPosByFloor();
        mc.player.connection.sendPacket(new CPacketPlayer.Position(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, true));
        mc.player.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
    }
    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (nullCheck()) {
            return;
        }
        if (PlayerUtil.getSlot(Item.getItemFromBlock(Blocks.OBSIDIAN)) != -1) {
            double x = mc.player.posX;
            double y = mc.player.posY;
            double z = mc.player.posZ;
            del++;
            if(del==Delay.getIntValue()){
                if(CenterSelf.isEnabled()){
                    Center();
                }
                if(mc.world.getBlockState(new BlockPos(x+1,y,z)).getBlock()==Blocks.AIR){PlayerUtil.changeSlot(PlayerUtil.getSlot(Item.getItemFromBlock(Blocks.OBSIDIAN)));PlayerUtil.placeBlock(new BlockPos(x+1,y,z), EnumHand.MAIN_HAND, PacketPLace.isEnabled());del=0;}
                if(mc.world.getBlockState(new BlockPos(x-1,y,z)).getBlock()==Blocks.AIR){PlayerUtil.changeSlot(PlayerUtil.getSlot(Item.getItemFromBlock(Blocks.OBSIDIAN)));PlayerUtil.placeBlock(new BlockPos(x-1,y,z), EnumHand.MAIN_HAND, PacketPLace.isEnabled());del=0;}
                if(mc.world.getBlockState(new BlockPos(x,y,z+1)).getBlock()==Blocks.AIR){PlayerUtil.changeSlot(PlayerUtil.getSlot(Item.getItemFromBlock(Blocks.OBSIDIAN)));PlayerUtil.placeBlock(new BlockPos(x,y,z+1), EnumHand.MAIN_HAND, PacketPLace.isEnabled());del=0;}
                if(mc.world.getBlockState(new BlockPos(x,y,z-1)).getBlock()==Blocks.AIR){PlayerUtil.changeSlot(PlayerUtil.getSlot(Item.getItemFromBlock(Blocks.OBSIDIAN)));PlayerUtil.placeBlock(new BlockPos(x,y,z-1), EnumHand.MAIN_HAND, PacketPLace.isEnabled());del=0;}
                if(mc.world.getBlockState(new BlockPos(x+1,y-1,z)).getBlock()==Blocks.AIR){PlayerUtil.changeSlot(PlayerUtil.getSlot(Item.getItemFromBlock(Blocks.OBSIDIAN)));PlayerUtil.placeBlock(new BlockPos(x+1,y-1,z), EnumHand.MAIN_HAND, PacketPLace.isEnabled());del=0;}
                if(mc.world.getBlockState(new BlockPos(x-1,y-1,z)).getBlock()==Blocks.AIR){PlayerUtil.changeSlot(PlayerUtil.getSlot(Item.getItemFromBlock(Blocks.OBSIDIAN)));PlayerUtil.placeBlock(new BlockPos(x-1,y-1,z), EnumHand.MAIN_HAND, PacketPLace.isEnabled());del=0;}
                if(mc.world.getBlockState(new BlockPos(x,y-1,z+1)).getBlock()==Blocks.AIR){PlayerUtil.changeSlot(PlayerUtil.getSlot(Item.getItemFromBlock(Blocks.OBSIDIAN)));PlayerUtil.placeBlock(new BlockPos(x,y-1,z+1), EnumHand.MAIN_HAND, PacketPLace.isEnabled());del=0;}
                if(mc.world.getBlockState(new BlockPos(x,y-1,z-1)).getBlock()==Blocks.AIR){PlayerUtil.changeSlot(PlayerUtil.getSlot(Item.getItemFromBlock(Blocks.OBSIDIAN)));PlayerUtil.placeBlock(new BlockPos(x,y-1,z-1), EnumHand.MAIN_HAND, PacketPLace.isEnabled());del=0;}
                if(mc.world.getBlockState(new BlockPos(x,y-1,z)).getBlock()==Blocks.AIR){PlayerUtil.changeSlot(PlayerUtil.getSlot(Item.getItemFromBlock(Blocks.OBSIDIAN)));PlayerUtil.placeBlock(new BlockPos(x,y-1,z), EnumHand.MAIN_HAND, PacketPLace.isEnabled());del=0;}
                if(SelfWeb.isEnabled()){
                    if(mc.world.getBlockState(new BlockPos(x,y,z)).getBlock()==Blocks.AIR) {
                        if (PlayerUtil.getSlot(Item.getItemFromBlock(Blocks.WEB)) != -1) {
                            int prevSlot = mc.player.inventory.currentItem;
                            PlayerUtil.changeSlot(PlayerUtil.getSlot(Item.getItemFromBlock(Blocks.WEB)));
                            PlayerUtil.placeBlock(new BlockPos(PlayerUtil.getPosFloored(mc.player)), EnumHand.MAIN_HAND, false);
                            mc.player.connection.sendPacket(new CPacketHeldItemChange(prevSlot));
                        } else {
                            Astro.sendMsg("You need to have webs to selfweb");
                            this.disable();
                        }
                    }
                }
                if(AutoDisable.isEnabled()){
                    this.disable();
                }
                del=0;
            }
        }else {
            Astro.sendMsg("You need to have obsidian to surround");
            this.disable();
        }
    }
}