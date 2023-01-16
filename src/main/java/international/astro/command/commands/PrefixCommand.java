package international.astro.command.commands;

import international.astro.Astro;
import international.astro.command.Command;

public class PrefixCommand extends Command {

    public PrefixCommand(String name, String[] alias) {
        super(name, alias);
    }

    @Override
    public void onTrigger(String arguments) {
        if(arguments.length() != 0){
            Astro.commandManager.setPrefix(arguments);
            Astro.sendMsg("Prefix has been set to "+ Astro.commandManager.getPrefix());
        }else{
            Astro.sendMsg("Please Enter a Prefix");
        }
        super.onTrigger(arguments);
    }
}
