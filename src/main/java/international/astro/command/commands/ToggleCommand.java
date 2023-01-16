package international.astro.command.commands;

import international.astro.Astro;
import international.astro.command.Command;
import international.astro.hack.Hack;
import org.lwjgl.input.Keyboard;

public class ToggleCommand extends Command {
    public ToggleCommand(String name, String[] alias) {
        super(name, alias);
    }

    @Override
    public void onTrigger(String arguments) {
        if(arguments.length() != 0){
            String[] split = arguments.split(" ");
            String hackName = split[0];
            Hack hack = Astro.hackManager.getHack(hackName);
            if(hack != null){
                hack.toggle();
            }else {
                Astro.sendMsg("Hack Does Not Exist");
            }

        }else{
            Astro.sendMsg("Please Enter a Hack");
        }
        super.onTrigger(arguments);
    }
}
