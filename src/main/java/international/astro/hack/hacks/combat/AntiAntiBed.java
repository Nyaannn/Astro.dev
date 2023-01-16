package international.astro.hack.hacks.combat;
import international.astro.hack.Hack;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "AntiAntiBed", description = "FUCKING STRING", category = Hack.Category.COMBAT)
public class AntiAntiBed extends Hack {

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (nullCheck()) {return;}
        for(TileEntity string : mc.world.loadedTileEntityList){
            if(string.getBlockType()== Block.getBlockFromItem(Items.STRING)) {
                if(string.isInvalid()){this.disable();}
                //Use for loop Nyaann
                for(int i=0;i < 6;i++){
                    mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, string.getPos(), EnumFacing.DOWN));
                }
            }
        }
    }
}