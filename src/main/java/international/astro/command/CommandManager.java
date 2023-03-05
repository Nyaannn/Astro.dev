package international.astro.command;

import international.astro.Astro;
import international.astro.command.commands.*;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;

public class CommandManager {
    private final ArrayList<Command> commands = new ArrayList<>();
    public static Minecraft mc = Minecraft.getMinecraft();
    public String prefix = "@";

    public CommandManager() {
        commands.add(new HelpCommand("Help", new String[]{"help"}));
        commands.add(new PrefixCommand("Prefix", new String[]{"prefix"}));
        commands.add(new BindCommand("Bind", new String[]{"bind"}));
        commands.add(new LennyCommand("Lenny", new String[]{"lenny"}));
        commands.add(new ToggleCommand("Toggle", new String[]{"toggle"}));
        commands.add(new TableFlipCommand("Tableflip", new String[]{"tableflip"}));
        commands.add(new SoundCommand("Sound", new String[]{"sound","snd"}));
        commands.add(new SaveCommand("Save", new String[]{"save"}));
        commands.add(new LoadCommand("Load", new String[]{"load"}));

    }

    public void runCommand(String args) {
        boolean found = false;
        String[] split = args.split(" ");
        String startCommand = split[0];
        String arguments = args.substring(startCommand.length()).trim();
        for (Command command : getCommands()) {
            for (String alias : command.getAlias()) {
                if (startCommand.equals(getPrefix() + alias)) {
                    command.onTrigger(arguments);
                    found = true;
                }
            }
        }
        if (!found) {
            Astro.sendMsg("Unknown command");
        }
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
