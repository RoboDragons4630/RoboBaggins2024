package frc.robot;

import edu.wpi.first.wpilibj.ADIS16448_IMU;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.autos.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* Controllers */
    private final Joystick driver = new Joystick(0);
    private final Joystick coDriver = new Joystick(1);
     /* Drive Controls */
    private final int translationAxis = Joystick.AxisType.kY.value;
    private final int strafeAxis = Joystick.AxisType.kX.value;
    private final int rotationAxis = Joystick.AxisType.kThrottle.value;

    /* Driver Buttons */
    private final JoystickButton zeroGyro = new JoystickButton(driver, 4);
    private final JoystickButton robotCentric = new JoystickButton(driver, 2);
    //For coDrivert to spin up motors
    private final JoystickButton launchButton = new JoystickButton(coDriver, 1);

    private final JoystickButton intakeButton = new JoystickButton(driver, 5);
    private final JoystickButton intakeReverse = new JoystickButton(driver, 1);
    private final JoystickButton intakeAngle = new JoystickButton(driver, 7);
    private final JoystickButton intakeAngleReverse = new JoystickButton(driver, 9);
    //for coDriver
    private final JoystickButton intakeDown = new JoystickButton(coDriver, 11);
    private final JoystickButton intakeUp = new JoystickButton(coDriver, 10);
    private final JoystickButton climberUp=  new JoystickButton(coDriver, 5);
    private final JoystickButton climberDown = new JoystickButton(coDriver, 4);

    /* Subsystems */
    public final Swerve s_Swerve = new Swerve();
    public final Intake s_Intake = new Intake();
    public final Launcher s_Launcher = new Launcher();
    public final Climber s_Climber = new Climber();
    //public final AutoCenter s_autoCenter = new AutoCenter();

    //private ADIS16448_IMU imu = new ADIS16448_IMU();


    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -driver.getRawAxis(translationAxis), 
                () -> -driver.getRawAxis(strafeAxis), 
                () -> -driver.getRawAxis(rotationAxis), 
                () -> robotCentric.getAsBoolean()
            )
        );

        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        /* Driver Buttons */
        zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroHeading()));
        intakeButton.onTrue(new IntakeCommand(s_Intake, Constants.intakeSpeed));
        intakeReverse.onTrue(new IntakeCommand(s_Intake, -1*Constants.intakeSpeed));
        launchButton.onTrue(new LaunchCommand(s_Launcher, Constants.launchSpeed));
        launchButton.onFalse(new LaunchCommand(s_Launcher, 0));
        intakeButton.onFalse(new IntakeCommand(s_Intake, 0));
        intakeReverse.onFalse(new IntakeCommand(s_Intake, 0));

        intakeAngle.onTrue(new IntakeAngleCommand(s_Intake, Constants.intakeAngleSpeed));
        intakeAngle.onFalse(new IntakeAngleCommand(s_Intake, 0));
        intakeAngleReverse.onTrue(new IntakeAngleCommand(s_Intake, -1*Constants.intakeAngleSpeed));
        intakeAngleReverse.onFalse(new IntakeAngleCommand(s_Intake, 0));
        intakeDown.onTrue(new LowerToIntake(s_Intake, -1*Constants.intakeAngleSpeed, 0.845));
        intakeUp.onTrue(new IntakeToHandoff(s_Intake, Constants.intakeAngleSpeed));

        climberUp.onTrue(new ClimbCommand(s_Climber, Constants.climberSpeed));
        climberDown.onTrue(new ClimbCommand(s_Climber, -Constants.climberSpeed));
        climberUp.onFalse(new ClimbCommand(s_Climber, 0));
        climberDown.onFalse(new ClimbCommand(s_Climber, 0));
        
        //SmartDashboard.putNumber("angleMotorDistance: ", s_Intake.getAngleMotorPosition());
        // intakeButton.whileTrue(new IntakeCommand(new Intake(), Constants.intakeSpeed));
        // intakeReverse.whileTrue(new IntakeCommand(new Intake(), -1*Constants.intakeSpeed));
        // intakeAngle.whileTrue(new IntakeAngleCommand(new Intake(), Constants.intakeAngleSpeed));
        // intakeAngleReverse.whileTrue(new IntakeAngleCommand(new Intake(), -1*Constants.intakeAngleSpeed));


    }
   /*  public ADIS16448_IMU getIMU(){
        return this.imu;
    }*/

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    //public Command getAutonomousCommand(int autoSelect) {


                    
                //new IntakeCommand(s_Intake, Constants.intakeSpeed).withTimeout(1));

            //, 
            //new IntakeToHandoff(s_Intake, Constants.intakeAngleSpeed))
  //  }
}
