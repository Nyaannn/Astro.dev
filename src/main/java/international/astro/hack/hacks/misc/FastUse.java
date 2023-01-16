package international.astro.hack.hacks.misc;
import international.astro.hack.Hack;
import international.astro.hack.option.options.ODouble;
import international.astro.mixins.accessor.IMinecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
@Hack.Construct(name = "FastUse", description = "items go BRRRRRR", category = Hack.Category.MISC)

public class FastUse extends Hack {
    public static FastUse getInstance = new FastUse();
    public static ODouble delay = new ODouble("Delay", 0, 100, 1, 0);
    public FastUse(){
        addOption(delay);
    }
    @SubscribeEvent
    public void onTick(TickEvent e){
        if(nullCheck()){return;}
        ((IMinecraft)mc).setRightClickDelayTimer(delay.getIntValue());
    }
}