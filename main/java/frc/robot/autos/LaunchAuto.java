// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos;

//import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.LaunchCommand;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Launcher;

public class LaunchAuto extends SequentialCommandGroup {

  /** Creates a new LaunchAuto. */
  public LaunchAuto(Intake s_Intake, Launcher s_Launcher) {
  
    // Use addRequirements() here to declare subsystem dependencies.

  // Called when the command is initially scheduled.


  // Called every time the scheduler runs while the command is scheduled.
    addCommands(
    new SequentialCommandGroup(
      new ParallelCommandGroup(
        new LaunchCommand(s_Launcher, Constants.launchSpeed).withTimeout(1.5),
        new IntakeCommand(s_Intake, -Constants.intakeSpeed).withTimeout(1))
    ));
  }
}
