// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.ArmHandoff;
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
public class AutoCenterFullLeftRing extends SequentialCommandGroup {
   public AutoCenterFullLeftRing(Launcher s_Launcher, Intake s_Intake, Swerve s_Swerve) {

  /** Creates a new AutoCenterFullLeftRing. */
  
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
new ParallelCommandGroup(
          new LaunchCommand(s_Launcher, Constants.launchSpeed).withTimeout(1.5),
          new IntakeCommand(s_Intake, -Constants.intakeSpeed).withTimeout(1)),
      new LowerToIntake(s_Intake, -0.3, 0.845).withTimeout(2),
      new ParallelCommandGroup(
          new SequentialCommandGroup(
              new exampleAuto(s_Swerve),
               new TeleopSwerve(s_Swerve, 
                  () ->0.0, 
                  () ->0.0, 
                  () ->0.0, 
                  () -> false).withTimeout(0.1)),
              
              //new IntakeCommand(s_Intake, Constants.intakeSpeed).withTimeout(1)
              //)
              
              //new LowerToIntake(s_Intake, -1*Constants.intakeAngleSpeed),
          new IntakeCommand(s_Intake, Constants.intakeSpeed).withTimeout(1.5)),
          new ParallelCommandGroup(
              new IntakeToHandoff(s_Intake, Constants.intakeAngleSpeed).withTimeout(2.5),
              new SequentialCommandGroup(
                  new exampleAutoReverse(s_Swerve),
                  new TeleopSwerve(s_Swerve, 
                      () ->0.0, 
                      () ->0.0, 
                      () ->0.0, 
                      () -> false).withTimeout(0.1))),
                      new IntakeCommand(s_Intake, Constants.intakeSpeed).withTimeout(.1),
                      new IntakeCommand(s_Intake, Constants.intakeSpeed).withTimeout(.1),
                      new ArmHandoff(s_Intake, Constants.intakeAngleSpeed).withTimeout(.2),
                  new ParallelCommandGroup(
                      new LaunchCommand(s_Launcher, Constants.launchSpeed).withTimeout(1.5),
                      new IntakeCommand(s_Intake, -Constants.intakeSpeed).withTimeout(1)),

          // Go to next ring
          new ParallelCommandGroup(
              new SequentialCommandGroup(
                  new exampleAutoLReverse(s_Swerve),
                  new TeleopSwerve(s_Swerve, 
                      () ->0.0, 
                      () ->0.0, 
                      () ->0.0, 
                      () -> false).withTimeout(0.1)),
              new LowerToIntake(s_Intake, -0.3, 0.845).withTimeout(2.5)
          ),
          new IntakeCommand(s_Intake, Constants.intakeSpeed).withTimeout(0.5)



    );
                    
                //new IntakeCommand(s_Intake, Constants.intakeSpeed).withTimeout(1));

            //, 
            //new IntakeToHandoff(s_Intake, Constants.intakeAngleSpeed))
    }
}

