// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

//import edu.wpi.first.wpilibj.Encoder;

public class Intake extends SubsystemBase {

  private final TalonFX angleMotor0 = new TalonFX(Constants.angleMotor0);
  private final TalonSRX spinMotor2 = new TalonSRX(Constants.spinMotor2);

  DutyCycleEncoder intakeEncoder;

  /** Creates a new Intake. */
  public Intake() {
    intakeEncoder = new DutyCycleEncoder(6);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("angleMotorDistance: ", getAngleMotorPosition());
    // This method will be called once per scheduler run
  }
 
  public void setIntakeAngle(double speed){
    angleMotor0.set(speed);
  }

  public double getAngleMotorPosition(){
    return intakeEncoder.getAbsolutePosition();
  }

  public void setIntakeSpin(double speed){
    spinMotor2.set(ControlMode.PercentOutput, speed);
  }
}
