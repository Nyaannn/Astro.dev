package international.astro.hack.hacks.combat;

import international.astro.hack.Hack;
import international.astro.hack.option.options.OList;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "AutoTotem", description = "self fill", category = Hack.Category.COMBAT)
public class AutoTotem extends Hack {
    private boolean switching = false;
    private int lastSlot;
    public AutoTotem() {
        addOption(mode);
    }
    OList mode = new OList("Hand:", "Off", "Main", "Off");
    @Override
    public void onEnable() {
        if(nullCheck()){return;}
    }
    @Override
    public void onDisable() {
        if(nullCheck()){return;}
    }
    @SubscribeEvent
    public void onUpdate(final TickEvent.ClientTickEvent event){
        if(nullCheck()){return;}
        if (switching) {
            swapTotem(lastSlot, 2);
            return;
        }
        if (mode.getMode().equalsIgnoreCase("main") && mc.player.getHeldItemOffhand().getItem() == Items.AIR) {
            swapTotem(getTotem(), 0);
        }
    }
    private int getTotem() {
        if (Items.TOTEM_OF_UNDYING == mc.player.getHeldItemOffhand().getItem()) return -1;
        for(int i = 45; i >= 0; i--) {
            final Item item = mc.player.inventory.getStackInSlot(i).getItem();
            if(item == Items.TOTEM_OF_UNDYING) {
                if (i < 9) {
                    return -1;
                }
                return i;
            }
        }
        return -1;
    }

    public void swapTotem(int slot, int step) {
        if (slot == -1) return;
        if (step == 0) {
            mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, mc.player);
            mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, mc.player);
            mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, mc.player);
        }
        if (step == 1) {
            mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, mc.player);
            switching = true;
            lastSlot = slot;
        }
        if (step == 2) {
            mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, mc.player);
            mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, mc.player);
            switching = false;
        }

        mc.playerController.updateController();
    }
}
