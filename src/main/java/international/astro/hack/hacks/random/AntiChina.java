package international.astro.hack.hacks.random;

import international.astro.hack.Hack;
import international.astro.hack.option.options.ODouble;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.Random;

@Hack.Construct(name = "AntiChina", description = "taiwan gang on top", category = Hack.Category.RANDOM)
public class AntiChina extends Hack {
    int delaythingie=0;
    ODouble delay = new ODouble("Delay", 20, 200, 1, 50);

    public AntiChina() {
        addOption(delay);
    }
    @Override
    public void onEnable() {
        delaythingie=0;
    }
    @Override
    public void onDisable() {
        delaythingie=0;
    }
    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (nullCheck()) {return;}

        delaythingie=delaythingie+1;
        if(delaythingie==delay.getIntValue()) {
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
            delaythingie=0;
        }

    }

}