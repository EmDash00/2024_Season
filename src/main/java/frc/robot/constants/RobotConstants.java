package frc.robot.constants;

import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;

/**
 * Software/hardware constants (e.g. CAN IDs, gear ratios, field measurements, etc.). For software
 * configs @see RobotConfig
 */
public final class RobotConstants {
  public static final class VisionConstants {
    public static final double camChassisXOffset = -1;
    public static final double camChassisYOffset = -1;
    public static final double camChassisZOffset = -1;

    public static final Transform3d robotToCam =
      new Transform3d(
        new Translation3d(camChassisXOffset, camChassisYOffset, camChassisZOffset),
        new Rotation3d(0,0,0));
  }
}
