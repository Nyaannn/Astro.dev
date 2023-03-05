package international.astro.command.commands;

import international.astro.command.Command;
import international.astro.util.file.Config;

public class SaveCommand extends Command {

    public SaveCommand(String name, String[] alias) {
        super(name, alias);
    }


    @Override
    public void onTrigger(String arguments) {
        Config.saveConfig();
        super.onTrigger(arguments);
    }
}
