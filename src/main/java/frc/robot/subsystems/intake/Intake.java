package frc.robot.subsystems.intake;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.RobotConfig;
import frc.robot.constants.RobotConstants.IntakeConstants;

public class Intake extends SubsystemBase {
  private final CANSparkMax m_angleMotor; // intake angle motor

  private final CANSparkMax m_rollerFollowerMotor; // left motor (Rollers)
  private final CANSparkMax m_rollerLeaderMotor; // right motor (Rollers)

  private final SparkPIDController m_rollerPidController; // Roller velocity PID controllers
  private final RelativeEncoder m_rollerEncoder; // Roller Relative Encoder

  private final SparkPIDController m_anglePidController; // Angle position PID controllers
  private final RelativeEncoder m_angleEncoder; // Angle Relative Encoder

  // Constructs intake and initializes motor, PID, encoder objects
  public Intake() {
    m_angleMotor = new CANSparkMax(IntakeConstants.kAngleMotorID, MotorType.kBrushless);
    m_rollerFollowerMotor = new CANSparkMax(IntakeConstants.kFollowerMotorID, MotorType.kBrushless);
    m_rollerLeaderMotor = new CANSparkMax(IntakeConstants.kLeaderMotorID, MotorType.kBrushless);
    m_rollerFollowerMotor.follow(this.m_rollerLeaderMotor);

    m_rollerPidController = m_rollerLeaderMotor.getPIDController();
    m_rollerEncoder = m_rollerLeaderMotor.getEncoder();

    m_anglePidController = m_angleMotor.getPIDController();
    m_angleEncoder = m_angleMotor.getEncoder();

    // set Roller PID coefficients
    m_rollerPidController.setP(RobotConfig.IntakeConfig.kRollerP);
    m_rollerPidController.setI(RobotConfig.IntakeConfig.kRollerI);
    m_rollerPidController.setD(RobotConfig.IntakeConfig.kRollerD);
    m_rollerPidController.setFF(RobotConfig.IntakeConfig.kRollerFF);

    // set Angle PID coefficients
    m_anglePidController.setP(RobotConfig.IntakeConfig.kAngleP);
    m_anglePidController.setI(RobotConfig.IntakeConfig.kAngleI);
    m_anglePidController.setD(RobotConfig.IntakeConfig.kAngleD);
    m_anglePidController.setFF(RobotConfig.IntakeConfig.kAngleFF);

    // display Roller PID coefficients on SmartDashboard
    SmartDashboard.putNumber("Roller P Gain", RobotConfig.IntakeConfig.kRollerP);
    SmartDashboard.putNumber("Roller I Gain", RobotConfig.IntakeConfig.kRollerI);
    SmartDashboard.putNumber("Roller D Gain", RobotConfig.IntakeConfig.kRollerD);
    SmartDashboard.putNumber("Roller Feed Forward", RobotConfig.IntakeConfig.kRollerFF);

    // display Angle PID coefficients on SmartDashboard
    SmartDashboard.putNumber("Angle P Gain", RobotConfig.IntakeConfig.kAngleP);
    SmartDashboard.putNumber("Angle I Gain", RobotConfig.IntakeConfig.kAngleI);
    SmartDashboard.putNumber("Angle D Gain", RobotConfig.IntakeConfig.kAngleD);
    SmartDashboard.putNumber("Angle Feed Forward", RobotConfig.IntakeConfig.kAngleFF);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  /**
   * Runs the intake
   *
   * @param motorOutput Motor speed from -1.0 to 1.0 as a percentage
   */
  public void run(double motorOutput) {
    m_rollerLeaderMotor.set(motorOutput);
  }

  /**
   * Runs the intake at a default speed The motor can be reversed by inputing a true as its
   * parameter
   *
   * @param isReversed The input to reverse the motor speed
   */
  public void run(boolean isReversed) {
    double runSpeed = isReversed ? -IntakeConstants.kDefaultSpeed : IntakeConstants.kDefaultSpeed;
    run(runSpeed);
  }

  // Stops the motor
  public void stop() {
    m_rollerLeaderMotor.stopMotor();
  }
}
