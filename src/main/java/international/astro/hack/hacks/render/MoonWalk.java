package international.astro.hack.hacks.render;

import international.astro.hack.Hack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "MoonWalk", description = "Walk like michael jackson", category = Hack.Category.RENDER)
public class MoonWalk extends Hack {

    @SubscribeEvent
    public void onUpdate(final TickEvent.ClientTickEvent event){
        if(nullCheck()){return;}
        for(EntityPlayer p : mc.world.playerEntities) {
            if(p != null) {
                p.limbSwing = 0;
                p.limbSwingAmount = 0;
                p.prevLimbSwingAmount = 0;
            }
        }
    }
}