package international.astro.hack.hacks.render;

import international.astro.events.PacketReceiveEvent;
import international.astro.hack.Hack;
import international.astro.hack.option.options.OBoolean;
import international.astro.hack.option.options.ODouble;
import net.minecraft.network.play.server.SPacketTimeUpdate;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "ViewTweaks", description = "Change perspective n shit", category = Hack.Category.RENDER)
public class ViewTweaks extends Hack {

    public OBoolean fullBright = new OBoolean("FullBright",  true);
    public OBoolean nw = new OBoolean("NoWeather",  true);
    public OBoolean wt = new OBoolean("ChangeTime",  true);
    public ODouble fovSlider = new ODouble("Fov", 75, 150, 1, 120);
    public ODouble worldTime = new ODouble("Time", 1, 20, 1, 10);
    public ODouble swingSpeed = new ODouble("Time", 1, 50, 1, 6);

    public OBoolean viewClip = new OBoolean("ViewClip", true);
    public static ViewTweaks getInstance = new ViewTweaks();

    public ViewTweaks() {
        addOption(fullBright);
        addOption(nw);
        addOption(fovSlider);
        addOption(wt);
        addOption(worldTime);
        addOption(viewClip);
        addOption(swingSpeed);
    }

    @Override
    public void onEnable()	{
        if (fullBright.isEnabled()) {
            mc.gameSettings.gammaSetting = 500;
        }

    }
    @Override
    public void onDisable()	{
        mc.gameSettings.gammaSetting = 0;
    }


    @SubscribeEvent
    public void onUpdate(final TickEvent.ClientTickEvent event){
        if(nullCheck()){return;}
        if(nw.isEnabled()){
            mc.world.setRainStrength(0);
            mc.world.setThunderStrength(0);
        }
        if(wt.isEnabled()){
            mc.world.setWorldTime((long)worldTime.getValue()*1000);
        }
        mc.gameSettings.fovSetting = fovSlider.getIntValue();

    }
    @SubscribeEvent
    public void onPacketReceived(PacketReceiveEvent e){
        if(wt.isEnabled()){
            if(e.getPacket() instanceof SPacketTimeUpdate){
                e.setCanceled(true);
            }
        }
    }
}
