package international.astro.hack.hacks.random;

import international.astro.hack.Hack;
import international.astro.hack.option.options.ODouble;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.Random;

@Hack.Construct(name = "AntiYonkie", description = "AntiYonkie", category = Hack.Category.MISC)
public class AntiYonkie extends Hack {
int delaythingie=0;
    ODouble delay = new ODouble("Delay", 20, 200, 1, 50);

    public AntiYonkie() {
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
            testicle.add("> _yonkie_ is a pedo "+hex);
            testicle.add("> _yonkie_ loves little boys "+hex);
            testicle.add("> _yonkie_ lives in a 3rd world country "+hex);
            testicle.add("> _yonkie_ is a retarded nigger chink "+hex);
            testicle.add("> _yonkie_ lives in a country were fucking 12 year olds is legal "+hex);
            testicle.add("> _yonkie_ wants to groom me "+hex);
            testicle.add("> _yonkie_ loves to finger his asshole to 6 year olds "+hex);
            testicle.add("> _yonkie_ is a shit pvper lel "+hex);
            testicle.add("> _yonkie_ cuts himself, its so funny "+hex);
            int index = (int)(Math.random()*testicle.size());
            mc.player.connection.sendPacket(new CPacketChatMessage(testicle.get(index)));
            delaythingie=0;
        }

    }

}