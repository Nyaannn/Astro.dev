package international.astro.command.commands;

import international.astro.Astro;
import international.astro.command.Command;
import international.astro.hack.Hack;
import org.lwjgl.input.Keyboard;

public class BindCommand extends Command {
    public BindCommand(String name, String[] alias) {
        super(name, alias);
    }

    @Override
    public void onTrigger(String arguments) {
        if(arguments.length() != 0){
            String[] split = arguments.split(" ");
            String key = split[1];
            String hackName = split[0];
            Hack hack = Astro.hackManager.getHack(hackName);
            if(hack != null){
                int keyboard = Keyboard.getKeyIndex(key.toUpperCase());
                if (key.equalsIgnoreCase("none")) {
                    keyboard = -1;
                }
                hack.setBind(keyboard);
                Astro.sendMsg("Bind for "+hackName+" has been set to "+key);

            }else {
                Astro.sendMsg("Hack Does Not Exist");
            }

        }else{
            Astro.sendMsg("Please Enter a Hack and a Bind");
        }
        return;
    }
}
