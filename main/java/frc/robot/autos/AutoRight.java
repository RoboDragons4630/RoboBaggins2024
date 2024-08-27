// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.IntakeToHandoff;
import frc.robot.commands.LaunchCommand;
import frc.robot.commands.LowerToIntake;
import frc.robot.commands.TeleopSwerve;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Launcher;
import frc.robot.subsystems.Swerve;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoRight extends SequentialCommandGroup {
  /** Creates a new AutoRight. */
  public AutoRight(Launcher s_Launcher, Intake s_Intake, Swerve s_Swerve) {
      

    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ParallelCommandGroup(
      new LaunchCommand(s_Launcher, Constants.launchSpeed).withTimeout(1.5),
      new IntakeCommand(s_Intake, -Constants.intakeSpeed).withTimeout(1)),
       new LowerToIntake(s_Intake, -Constants.intakeAngleSpeed, 0.865).withTimeout(2.5),
       new ParallelCommandGroup(
        new SequentialCommandGroup(
          new InstantCommand(() -> s_Swerve.zeroHeading()),
        new exampleAutoS(s_Swerve),
        //new exampleAutoSForward(s_Swerve),
         new TeleopSwerve(s_Swerve, 
                            () ->0.0, 
                            () ->0.0, 
                            () ->0.0, 
                            () -> false).withTimeout(0.1))
       //new IntakeCommand(s_Intake, Constants.intakeSpeed).withTimeout(2.3)
       ), 
       new InstantCommand(() -> s_Swerve.zeroHeading()),
       new exampleAutoSForward(s_Swerve)
      );
       /*new ParallelCommandGroup(
        //new IntakeCommand(s_Intake, Constants.intakeSpeed).withTimeout(2.3),
        new SequentialCommandGroup(
        new InstantCommand(() -> s_Swerve.zeroHeading()),
        new exampleAutoSForward(s_Swerve),
         new TeleopSwerve(s_Swerve, 
                            () ->0.0, 
                            () ->0.0, 
                            () ->0.0, 
                            () -> false).withTimeout(0.1))
       ),
        new InstantCommand(() -> s_Swerve.zeroHeading()),
       new exampleAutoSReverse(s_Swerve)
       );
       //new Turn(s_Swerve)));
       /*  new TeleopSwerve(s_Swerve, 
                            () ->0.0, 
                            () ->0.0, 
                            () ->0.0, 
                            () -> false).withTimeout(0.1),
        new exampleAutoS(s_Swerve),
        new TeleopSwerve(s_Swerve, 
                            () ->0.0, 
                            () ->0.0, 
                            () ->0.0, 
                            () -> false).withTimeout(0.1)),
                  
        new IntakeCommand(s_Intake, Constants.intakeSpeed).withTimeout(2.3))  */
       // new IntakeCommand(s_Intake, Constants.intakeSpeed).withTimeout(1)
      /*   new ParallelCommandGroup(
              new IntakeToHandoff(s_Intake, Constants.intakeAngleSpeed).withTimeout(2.5),
              new SequentialCommandGroup(
                  new exampleAutoSReverse(s_Swerve),
                  new TeleopSwerve(s_Swerve, 
                      () ->0.0, 
                      () ->0.0, 
                      () ->0.0, 
                      () -> false).withTimeout(0.1)))*/ 
  
  }
}
