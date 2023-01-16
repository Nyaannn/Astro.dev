package international.astro.command.commands;

import international.astro.Astro;
import international.astro.command.Command;

public class HelpCommand extends Command {

    public HelpCommand(String name, String[] alias) {
        super(name, alias);
    }

    @Override
    public void onTrigger(String arguments) {
        Astro.sendMsg("The List of current commands");
        Astro.sendMsg("============================");

        Astro.sendMsg(Astro.commandManager.getPrefix()+"help");
        Astro.sendMsg("The help command");

        Astro.sendMsg(Astro.commandManager.getPrefix()+"prefix");
        Astro.sendMsg("You can change the prefix with this command.");

        Astro.sendMsg(Astro.commandManager.getPrefix()+"bind");
        Astro.sendMsg("You can bind hacks with this command.");

        Astro.sendMsg(Astro.commandManager.getPrefix()+"lenny");
        Astro.sendMsg("lenny...");
    }
}
