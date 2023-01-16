package international.astro.command.commands;

import international.astro.command.Command;

public class TableFlipCommand extends Command {

    public TableFlipCommand(String name, String[] alias) {
        super(name, alias);
    }

    private final String LENNY = "(╯°□°)╯︵ ┻━┻";

    @Override
    public void onTrigger(String arguments) {
        mc.player.sendChatMessage(LENNY);
        super.onTrigger(arguments);
    }
}
