// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Launcher extends SubsystemBase {
  private final TalonFX spinMotor0 = new TalonFX(Constants.spinMotor0);
  private final TalonFX spinMotor1 = new TalonFX(Constants.spinMotor1);
  /** Creates a new Launcher. */
  public Launcher() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setSpinMotors(double speed) {
    spinMotor0.set(-speed);
    spinMotor1.set(speed);
  }
}
