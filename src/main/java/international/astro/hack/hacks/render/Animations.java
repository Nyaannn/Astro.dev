package international.astro.hack.hacks.render;

import international.astro.hack.Hack;
import international.astro.hack.option.options.ODouble;
import international.astro.hack.option.options.OList;

@Hack.Construct(name = "Animations", description = "make stuff do cool things", category = Hack.Category.RENDER)
public class Animations extends Hack {

    public OList mode = new OList("Mode", "Dortware", "Dortware");
    public ODouble swingSpeed = new ODouble("Speed",1,15,1,5);
    public static Animations getInstance = new Animations();

    public Animations() {
        addOption(mode);
        addOption(swingSpeed);
    }

}
