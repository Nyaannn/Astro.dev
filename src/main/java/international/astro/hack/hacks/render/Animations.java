package international.astro.hack.hacks.render;

import international.astro.hack.Hack;
import international.astro.hack.option.options.ODouble;
import international.astro.hack.option.options.OList;

@Hack.Construct(name = "Animations", description = "make stuff do cool things", category = Hack.Category.RENDER)
public class Animations extends Hack {

    public OList mode = new OList("Mode", "Dortware", "Astro","Dortware", "Astolfo");
    public ODouble scale = new ODouble("Scale", -10,10,1,1);
    public static Animations getInstance = new Animations();

    public Animations() {
        addOption(mode);
        addOption(scale);
    }

}
