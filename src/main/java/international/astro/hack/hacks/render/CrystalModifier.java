package international.astro.hack.hacks.render;

import international.astro.hack.Hack;
import international.astro.hack.option.options.ODouble;

@Hack.Construct(name = "CrystalModifier", description = "Modifies Crystal", category = Hack.Category.RENDER)
public class CrystalModifier extends Hack {

    public static ODouble scale = new ODouble("Scale",0.1,1,0.1,0.35);
    public static ODouble spinSpeed = new ODouble("SpinSpeed",0.1,50,0.1,3);
    public static ODouble bounce = new ODouble("Bounce",0,10,0.1,0.2);


    public CrystalModifier(){
        addOption(scale);
        addOption(spinSpeed);
        addOption(bounce);
    }
}
