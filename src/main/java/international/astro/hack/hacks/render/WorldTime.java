package international.astro.hack.hacks.render;

import international.astro.events.PacketReceiveEvent;
import international.astro.hack.Hack;
import international.astro.hack.option.options.ODouble;
import net.minecraft.network.play.server.SPacketTimeUpdate;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "WorldTime", description = "Change World Time", category = Hack.Category.RENDER)
public class WorldTime extends Hack {

    public ODouble worldTime = new ODouble("Time", 1, 20, 1, 10);

    public WorldTime(){
        addOption(worldTime);
    }

    @SubscribeEvent
    public void onUpdate(final TickEvent.ClientTickEvent event){
        if(nullCheck()){return;}
        mc.world.setWorldTime((long)worldTime.getValue()*1000);
    }
    @SubscribeEvent
    public void onPacketReceived(PacketReceiveEvent e){
        if(e.getPacket() instanceof SPacketTimeUpdate){
            e.setCanceled(true);
        }
    }

}
