package international.astro.hack.hacks.movement;


import international.astro.events.PostMotionEvent;
import international.astro.events.PreMotionEvent;
import international.astro.hack.Hack;
import international.astro.hack.option.options.OList;
import international.astro.util.TimerUtil;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.MovementInput;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "NoSlow", description = "NoSlowdown", category = Hack.Category.MOVEMENT)
public class NoSlow extends Hack {
    OList mode = new OList("Mode:", "Vanilla", "TooBeeTooTea", "Vanilla","Hypixel");
    TimerUtil timer = new TimerUtil();
    public NoSlow() {
        addOption(mode);
    }

    @SubscribeEvent
    public void onInput(InputUpdateEvent e){
        if(nullCheck()){return;}
        if(mode.getMode().equalsIgnoreCase("Vanilla")) {
            if (mc.player.isHandActive()) {
                MovementInput movementInput = e.getMovementInput();
                movementInput.moveStrafe *= 5.0f;
                movementInput.moveForward *= 5.0f;
            }
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent e){
        if(nullCheck()){return;}
        if(mode.getMode().equalsIgnoreCase("TooBeeTooTea")) {
            if (mc.player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getItem() == Items.GOLDEN_APPLE && mc.gameSettings.keyBindUseItem.isKeyDown()) {
                mc.player.connection.sendPacket(new CPacketHeldItemChange(findGappleInHotbar()));
            }
        }
    }

    @SubscribeEvent
    public void onPreMotionEvent(PreMotionEvent e) {
        if(nullCheck()){return;}
        if(mode.isMode("Hypixel")) {
            if (!mc.player.onGround) {return;}
            if (mc.player.ticksExisted % 2 == 0) {
                mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, new BlockPos(-1, -1, -1), EnumFacing.DOWN));
            }
        }
    }

    @SubscribeEvent
    public void onPostMotionEvent(PostMotionEvent e) {
        if(nullCheck()){return;}
        if(mode.isMode("Hypixel")) {
            if (!mc.player.onGround) {return;}
            if (mc.player.ticksExisted % 2 != 0) {
                mc.player.connection.sendPacket(new CPacketPlayerTryUseItemOnBlock(new BlockPos(-1, -1, -1), EnumFacing.DOWN, EnumHand.MAIN_HAND, 0f, 0f, 0f));
            }
        }
    }


    private int findGappleInHotbar(){
        int slot = 0;
        for(int i=0; i<9; i++){
            if(mc.player.inventory.getStackInSlot(i).getItem() != Items.GOLDEN_APPLE) continue;
            slot = i;
            break;
        }
        return slot;
    }
}