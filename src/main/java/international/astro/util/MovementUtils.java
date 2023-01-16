package international.astro.util;

import net.minecraft.client.Minecraft;

public class MovementUtils {
    public static Minecraft mc = Minecraft.getMinecraft();

    //makes u strafe
    public static void strafe() {
        strafe(getSpeed());
    }

    //Gets player speed
    public static float getSpeed() {
        return (float)Math.sqrt(mc.player.motionX * mc.player.motionX + mc.player.motionZ * mc.player.motionZ);
    }

    public static void strafe(float speed) {
        if (!isMoving()){return;}
        double yaw = getDirection();
        mc.player.motionX = -Math.sin(yaw) * speed;
        mc.player.motionZ = Math.cos(yaw) * speed;

    }

    //Checks if player is moving
    public static boolean isMoving() {
        return (mc.player != null && mc.player.movementInput.moveForward != 0f || mc.player.movementInput.moveStrafe != 0f);
    }

    public static double getDirection() {
        float rotationYaw = mc.player.rotationYaw;
        if (mc.player.moveForward < 0.0F)
            rotationYaw += 180.0F;
        float forward = 1.0F;
        if (mc.player.moveForward < 0.0F) {
            forward = -0.5F;
        } else if (mc.player.moveForward > 0.0F) {
            forward = 0.5F;
        }
        if (mc.player.moveStrafing > 0.0F)
            rotationYaw -= 90.0F * forward;
        if (mc.player.moveStrafing < 0.0F)
            rotationYaw += 90.0F * forward;
        return Math.toRadians(rotationYaw);
    }
}
