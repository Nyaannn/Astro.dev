package international.astro.events;

import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class PreMotionEvent extends Event {
    public double x;
    public double y;
    public double z;
    public float rotationYaw;
    public float rotationPitch;
    public boolean onGround;
    public boolean modified;

    public PreMotionEvent(final double x, final double y, final double z, final float rotationYaw, final float rotationPitch, final boolean onGround) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.rotationYaw = rotationYaw;
        this.rotationPitch = rotationPitch;
        this.onGround = onGround;
    }


    public boolean isModified() {
        return this.modified;
    }

    public double getX() {
        return this.x;
    }

    public void setX(final double x) {
        this.modified = true;
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(final double y) {
        this.modified = true;
        this.y = y;
    }

    public double getZ() {
        return this.z;
    }

    public void setZ(final double z) {
        this.modified = true;
        this.z = z;
    }

    public float getYaw() {
        return this.rotationYaw;
    }

    public void setYaw(final float rotationYaw) {
        this.modified = true;
        this.rotationYaw = rotationYaw;
    }

    public float getPitch() {
        return this.rotationPitch;
    }

    public void setPitch(final float rotationPitch) {
        this.modified = true;
        this.rotationPitch = rotationPitch;
    }

    public boolean isOnGround() {
        return this.onGround;
    }

    public void setOnGround(final boolean onGround) {
        this.modified = true;
        this.onGround = onGround;
    }
}
