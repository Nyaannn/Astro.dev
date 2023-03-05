package international.astro.hack.hacks.render;

import international.astro.hack.Hack;
import international.astro.hack.option.options.OBoolean;
import international.astro.hack.option.options.ODouble;

@Hack.Construct(name = "ViewClip", description = "Change perspective n shit", category = Hack.Category.RENDER)
public class ViewClip extends Hack {

    public ODouble distance = new ODouble("Distance",1,20,1,5);
    public static ViewClip getInstance = new ViewClip();

    public ViewClip() {
        addOption(distance);
    }
}
