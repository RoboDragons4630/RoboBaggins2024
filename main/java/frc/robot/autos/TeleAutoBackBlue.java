// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.LaunchCommand;
import frc.robot.commands.TeleopSwerve;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Launcher;
import frc.robot.subsystems.Swerve;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TeleAutoBackBlue extends SequentialCommandGroup {
  /** Creates a new TeleAutoBackBlue. */
  public TeleAutoBackBlue(Launcher s_Launcher, Intake s_Intake, Swerve s_Swerve) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new SequentialCommandGroup(
      new ParallelCommandGroup(
        new LaunchCommand(s_Launcher, Constants.launchSpeed).withTimeout(1.5),
        new IntakeCommand(s_Intake, -Constants.intakeSpeed).withTimeout(1)),
      new TeleopSwerve(s_Swerve, 
              () ->0.3, 
              () ->0.0, 
              () ->0.0, 
              () -> true).withTimeout(1.5),
      new TeleopSwerve(s_Swerve, 
              () ->0.0, 
              () ->0.0, 
              () ->0.0, 
              () -> true).withTimeout(0.1),
      new TeleopSwerve(s_Swerve, 
              () ->0.0, 
              () ->0.0, 
              () ->0.3, 
              () -> true).withTimeout(0.5),
      new TeleopSwerve(s_Swerve, 
              () ->0.0, 
              () ->0.0, 
              () ->0.0, 
              () -> true).withTimeout(0.1),
      new TeleopSwerve(s_Swerve, 
              () ->0.3, 
              () ->0.0, 
              () ->0.0, 
              () -> true).withTimeout(1.5)
    )
    );
  }
}
