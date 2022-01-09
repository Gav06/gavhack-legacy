package gavhack.module.movement;

import com.darkmagician6.eventapi.EventTarget;
import gavhack.event.EventPlayerTick;
import gavhack.module.Module;
import gavhack.setting.Setting;
import org.lwjgl.input.Keyboard;

public class Speed extends Module {
    public Speed() {
        super("Speed", Category.MOVEMENT, Keyboard.KEY_I);
    }

    public static final Setting<Double> multiplier = new Setting<>("Multiplier", 0.2, 0.0, 2.0);

    private double speed = 0.0;
    private boolean speedUp = false;

    @EventTarget
    public void onTick(EventPlayerTick event) {
        if (mc.thePlayer.movementInput.moveForward != 0.0f || mc.thePlayer.movementInput.moveStrafe != 0.0f) {
            speed = mc.thePlayer.isSprinting() ? 0.221 : 0.2873;

            if (mc.thePlayer.onGround) {
                mc.thePlayer.motionY = 0.3999999;

                speed *= 2.149;
                speedUp = !speedUp;
                if (speedUp) {
                    speed += multiplier.getValue();
                }
            }

            double[] motion = getMotion(speed);

            mc.thePlayer.motionX = motion[0];
            mc.thePlayer.motionZ = motion[1];
        }
    }

    private double[] getMotion(double speed) {
        float[] movements = getMovement();

        float forward = movements[0];
        float strafe = movements[1];

        double sin = -Math.sin(Math.toRadians(movements[2]));
        double cos = Math.cos(Math.toRadians(movements[2]));

        return new double[] {
                forward * speed * sin + strafe * speed * cos,
                forward * speed * cos - strafe * speed * sin
        };
    }

    private float[] getMovement() {
        float forward = mc.thePlayer.movementInput.moveForward;
        float strafe = mc.thePlayer.movementInput.moveStrafe;
        float yaw = mc.thePlayer.rotationYaw;

        if (forward != 0.0f) {
            if (strafe > 0.0f) {
                yaw += forward > 0.0f ? -45.0f : 45.0f;
            } else if (strafe < 0.0f) {
                yaw += forward > 0.0f ? 45.0f : -45.0f;
            }

            strafe = 0.0f;
            if (forward > 0.0f) {
                forward = 1.0f;
            } else if (forward < 0.0f) {
                forward = -1.0f;
            }
        }

        return new float[] { forward, strafe, yaw, };
    }
}
