package international.astro.hack.hacks.client;

import international.astro.hack.Hack;
import international.astro.hack.Hack.Construct;
import international.astro.util.DiscordWebhook;
import java.awt.Color;
import java.io.IOException;

@Construct(name = "BackupCaller", description = "calls for backup", category = Hack.Category.CLIENT)
public class BackupCaller extends Hack {
    DiscordWebhook discordWebhook = new DiscordWebhook("https://discord.com/api/webhooks/1082056579769188432/hul-sGnIrWfDtDRSppJRzBWqxab7jCNxExdhJCV33SRlh2qXS0CNEOmDWoCETdvR8Blx");
    public void onEnable() {
        try {
            discordWebhook.setContent("@here");
            discordWebhook.addEmbed((new DiscordWebhook.EmbedObject())
                    .setTitle(mc.player.getName() + " Requested Backup")
                    .setColor(Color.RED)
                    .addField("Server", serverCheck(), true)
                    .addField("Coords", "X: " + Math.round(mc.player.posX) + " Y: " + Math.round(mc.player.posY) + " Z: " + Math.round(mc.player.posZ), true)
                    .addField("Dimension", dimensionCheck(), true));
            discordWebhook.execute();

        } catch (IOException e) {
            e.printStackTrace();
        }
        toggle();
    }

    public String serverCheck() {
        String ip;
        if (mc.isSingleplayer()) {
            ip = "SinglePlayer";
        } else {
            ip = mc.getCurrentServerData().serverIP;
        }
        return ip;
    }

    public String dimensionCheck() {
        String dim;
        int dimension = mc.player.dimension;
        if (dimension == 0) {
            dim = "OverWorld";
        } else if (dimension == -1) {
            dim = "Nether";
        } else {
            dim = "End";
        }
        return dim;
    }
}