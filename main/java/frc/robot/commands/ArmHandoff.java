// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class ArmHandoff extends Command {
  private final Intake intake;
  private final double speed;
  
  /** Creates a new ArmHandoff. */
  public ArmHandoff(Intake intake, double speed) {
    this.intake = intake;
    this.speed = speed;

    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      if (intake.getAngleMotorPosition() > 0.35){
      intake.setIntakeAngle(speed);
    } else {
       intake.setIntakeAngle(0);
    }}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.setIntakeAngle(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
