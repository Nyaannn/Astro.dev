package international.astro.command.commands;

import international.astro.command.Command;

public class LennyCommand extends Command {

    public LennyCommand(String name, String[] alias) {
        super(name, alias);
    }

    private final String LENNY = "( ͡° ͜ʖ ͡°)";

    @Override
    public void onTrigger(String arguments) {
        mc.player.sendChatMessage(LENNY);
        super.onTrigger(arguments);
    }
}
