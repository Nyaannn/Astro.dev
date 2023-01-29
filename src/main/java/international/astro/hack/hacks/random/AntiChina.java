package international.astro.hack.hacks.random;

import international.astro.hack.Hack;
import international.astro.hack.option.options.ODouble;
import international.astro.util.TimerUtil;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.Random;

@Hack.Construct(name = "AntiChina", description = "taiwan gang on top", category = Hack.Category.RANDOM)
public class AntiChina extends Hack {
    ODouble delay = new ODouble("Delay", 20, 500, 1, 50);
    TimerUtil timer = new TimerUtil();

    public AntiChina() {
        addOption(delay);
    }
    @Override
    public void onEnable() {
        timer.reset();
    }
    @Override
    public void onDisable() {
        timer.reset();
    }
    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (nullCheck()) {return;}
        if(timer.passedMs(delay.getValue())) {
            Random random = new Random();
            int nextInt = random.nextInt(256*256*256);
            String hex = String.format("#%06x", nextInt);
            ArrayList<String> testicle = new ArrayList<String>();
            testicle.clear();
            testicle.add("> Taiwan owning "+hex);
            testicle.add("> Chinese ppl love little boys "+hex);
            testicle.add("> Taiwan > China"+hex);
            testicle.add("> Xi Jinping < Tsai Ing-wen "+hex);
            testicle.add("> Taiwan owns china"+hex);
            testicle.add("> Chinese players are robot pvper's lel "+hex);
            testicle.add("> Glory to Taiwan "+hex);
            int index = (int)(Math.random()*testicle.size());
            mc.player.connection.sendPacket(new CPacketChatMessage(testicle.get(index)));
            timer.reset();
        }

    }

}