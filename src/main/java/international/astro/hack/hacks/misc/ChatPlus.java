package international.astro.hack.hacks.misc;

import international.astro.events.PacketReceiveEvent;
import international.astro.hack.Hack;
import international.astro.hack.option.options.OBoolean;
import international.astro.util.TimerUtil;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.lwjgl.input.Keyboard;

import java.util.Random;

@Hack.Construct(name = "Chatplus", description = "Chat but better", category = Hack.Category.MISC)
public class ChatPlus extends Hack {

    public OBoolean chatSuffix = new OBoolean("ChatSuffix", true);
    public OBoolean spamBypass = new OBoolean("SpamBypass", true);

    public OBoolean greenText = new OBoolean("GreenText", true);
    public OBoolean clearChat = new OBoolean("ClearChat", true);
    public OBoolean portalChat = new OBoolean("PortalChat", true);
    public OBoolean RedText = new OBoolean("RedText", false);
    public OBoolean YellowText = new OBoolean("YellowText", false);
    public OBoolean BlueText = new OBoolean("BlueText", false);

    public static ChatPlus getInstance = new ChatPlus();

    public ChatPlus() {
        addOption(chatSuffix);
        addOption(spamBypass);
        addOption(greenText);
        addOption(clearChat);
        addOption(portalChat);
        addOption(RedText);
        addOption(YellowText);
        addOption(BlueText);
    }


    @SubscribeEvent
    public void onChat(ClientChatEvent e) {
        if (nullCheck()) {return;}

        //gen random hex
        Random random = new Random();
        int nextInt = random.nextInt(256 * 256 * 256);
        String hex = String.format("#%06x", nextInt);

        //check if command
        if (e.getMessage().startsWith("/") || e.getMessage().startsWith(".")
                || e.getMessage().startsWith(",") || e.getMessage().startsWith("-")
                || e.getMessage().startsWith("$") || e.getMessage().startsWith("*")
                || e.getMessage().startsWith("!") || e.getMessage().startsWith("?")
                || e.getMessage().startsWith("+") || e.getMessage().startsWith("@")
                || e.getMessage().startsWith("~")) {
            return;

        } else {
            if (chatSuffix.isEnabled()) {
                e.setMessage(e.getMessage() + " | [Astro.dev]");
            }
            if (spamBypass.isEnabled()) {
                e.setMessage(e.getMessage() + "[" + hex + "]");
            }
            if (greenText.isEnabled()) {
                e.setMessage("> " + e.getMessage());
            }
            if (RedText.isEnabled()) {
                e.setMessage("< " + e.getMessage());

            }
            if (YellowText.isEnabled()) {
                e.setMessage("! " + e.getMessage());

            }
            if (BlueText.isEnabled()) {
                e.setMessage("` " + e.getMessage());

            }

        }
    }
}