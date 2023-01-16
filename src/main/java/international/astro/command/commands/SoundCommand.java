package international.astro.command.commands;
import international.astro.command.Command;
import international.astro.mixins.accessor.ISoundHandler;

public class SoundCommand extends Command {
    public SoundCommand(String name, String[] alias) {
        super(name, alias);
    }
    @Override
    public void onTrigger(String arguments) {
        ISoundHandler soundManager =((ISoundHandler)mc.getSoundHandler());
        soundManager.getManager().reloadSoundSystem();
        super.onTrigger(arguments);

    }
}