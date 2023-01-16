package international.astro.events;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class AttackEvent extends Event {
    public Entity target;

    public AttackEvent(Entity target) {
        this.target = target;
    }

    public Entity getTarget() {
        return target;
    }
}
