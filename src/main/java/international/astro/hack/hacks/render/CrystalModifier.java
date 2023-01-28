package international.astro.hack.hacks.render;

import international.astro.hack.Hack;
import international.astro.hack.option.options.ODouble;

@Hack.Construct(name = "CrystalModifier(WIP)", description = "Modifies Crystal", category = Hack.Category.RENDER)
public class CrystalModifier extends Hack {

    public static ODouble scale = new ODouble("CrystalScale",0.1,1,0.1,0.3);
    //public static ODouble speed = new ODouble("SpinSpeed",0,100,1,40);
    public CrystalModifier(){
        addOption(scale);
        //addOption(bob);
        //addOption(speed);
    }
}
