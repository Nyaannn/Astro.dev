package international.astro.hack.hacks.random;

import international.astro.hack.Hack;
import international.astro.hack.option.options.ODouble;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;
@Hack.Construct(name = "AutoPedo", description = "Baits retards", category = Hack.Category.RANDOM)
public class AutoPedo extends Hack {
int delaythingie=0;
    ODouble delay = new ODouble("Delay", 20, 200, 1, 50);

    public AutoPedo() {
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

        delaythingie=delaythingie+1;
        if(delaythingie==delay.getIntValue()) {
            ArrayList<String> testicle = new ArrayList<String>();
            testicle.clear();
            testicle.add("> <AutoPedo> My Fav website is pornhub.com/kids");
            testicle.add("> <AutoPedo> Guys im connorqq i love little boys");
            testicle.add("> <AutoPedo> guys do you want 9694956TB of little boy pics go to <grabify url here>");
            testicle.add("> <AutoPedo> edp445 is my daddy");
            testicle.add("> <AutoPedo> i wanna be banged by chris hanson from to catch a predetor");
            testicle.add("> <AutoPedo> edp is in my sex dungeon");
            testicle.add("> <AutoPedo> i inject heroin in my dick to get hard");
            testicle.add("> <AutoPedo> 25st assraped Popbob");
            testicle.add("> <AutoPedo> I love little boys");
            testicle.add("> <AutoPedo> real");
            int index = (int)(Math.random()*testicle.size());
            mc.player.connection.sendPacket(new CPacketChatMessage(testicle.get(index)));
            delaythingie=0;
        }

    }

}