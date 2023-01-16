package international.astro.events;

import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class PacketReceiveEvent extends Event {

    private Packet p;


    public PacketReceiveEvent(Packet p) {
        this.p = p;
    }

    public Packet getPacket() {
        return p;
    }
}
