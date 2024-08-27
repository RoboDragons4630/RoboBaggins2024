// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class LowerToIntake extends Command {
  private final Intake intake;
  private final double speed;
  private final double encoderValue;
  
  /** Creates a new LowerToIntake. */
  public LowerToIntake(Intake intake, double speed, double encoderValue) {
    this.intake = intake;
    this.speed = speed;
    this.encoderValue = encoderValue;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (intake.getAngleMotorPosition() < encoderValue) {
      intake.setIntakeAngle(speed);
    } else {
      intake.setIntakeAngle(0);
     
    }
  }

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
