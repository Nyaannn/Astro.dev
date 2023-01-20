package international.astro.hack.hacks.combat;

import international.astro.hack.Hack;

@Hack.Construct(name = "Elevator", description = "EDestroyer10#4940 was beggin for me to add this", category = Hack.Category.COMBAT)
public class Elevator extends Hack {

    @Override
    public void onEnable() {
        if(nullCheck()){return;}

        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
