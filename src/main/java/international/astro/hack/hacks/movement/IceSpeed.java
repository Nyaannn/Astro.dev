package international.astro.hack.hacks.movement;

import international.astro.hack.Hack;
import international.astro.hack.option.options.ODouble;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "IceSpeed", description = "IceSpeed", category = Hack.Category.MOVEMENT)
public class IceSpeed extends Hack {
    public static ODouble Speed = new ODouble("Speed", 0.1, 50, 1, 3);

    public IceSpeed() {
        addOption(Speed);
    }
    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (nullCheck()) {return;}
        Blocks.ICE.setDefaultSlipperiness(Speed.getIntValue());
        Blocks.PACKED_ICE.setDefaultSlipperiness(Speed.getIntValue());
        Blocks.FROSTED_ICE.setDefaultSlipperiness(Speed.getIntValue());
    }
    @Override
    public void onDisable() {
        Blocks.ICE.setDefaultSlipperiness(0.98f);
        Blocks.PACKED_ICE.setDefaultSlipperiness(0.98f);
        Blocks.FROSTED_ICE.setDefaultSlipperiness(0.98f);
        super.onDisable();
    }
}