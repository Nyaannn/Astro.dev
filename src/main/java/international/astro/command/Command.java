package international.astro.command;

import net.minecraft.client.Minecraft;

public class Command {
    public static Minecraft mc = Minecraft.getMinecraft();
    private String name;
    private String[] alias;

    public Command(String name, String[] alias) {
        setName(name);
        setAlias(alias);
    }

    public void onTrigger(String arguments) {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getAlias() {
        return alias;
    }

    public void setAlias(String[] alias) {
        this.alias = alias;
    }

    public void sendMsg(String s){
        mc.player.sendChatMessage("[Astro.dev]"+ s);
    }



}
