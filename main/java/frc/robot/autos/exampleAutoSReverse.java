// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos;

import java.util.List;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Swerve;

/** Add your docs here. */
public class exampleAutoSReverse extends SequentialCommandGroup {
    public exampleAutoSReverse(Swerve s_Swerve){
        TrajectoryConfig config =
            new TrajectoryConfig(
                    Constants.AutoConstants.kMaxSpeedMetersPerSecond,
                    Constants.AutoConstants.kMaxAccelerationMetersPerSecondSquared)
                .setKinematics(Constants.Swerve.swerveKinematics);

        // An example trajectory to follow.  All units in meters.
        //Rotation2d Rotation2d = new Rotation2d();
        Trajectory exampleTrajectory =
            TrajectoryGenerator.generateTrajectory(
                // Start at the origin facing the +X direction
                //          1.5, 1.5
                new Pose2d(1.5, 0, new Rotation2d(0)),
                // Pass through these two interior waypoints, making an 's' curve path
               List.of(new Translation2d(1, 0), new Translation2d(0.5, 0)),
                // End 3 meters straight ahead of where we started, facing forward
                //          0, 1.5                       -45
                new Pose2d(0, 0, Rotation2d.fromDegrees(60)),
            config.setReversed(true));
            /*
        Trajectory exampleTrajectory2 =
            TrajectoryGenerator.generateTrajectory(
                new Pose2d(1.5, 0, new Rotation2d(0)),
                // Pass through these two interior waypoints, making an 's' curve path
               List.of(new Translation2d(1.5, 0), new Translation2d(0.5, 0)),
                // End 3 meters straight ahead of where we started, facing forward
                new Pose2d(0, 0, new Rotation2d()),
            config.setReversed(true));
        /* */

        var thetaController =
            new ProfiledPIDController(
                Constants.AutoConstants.kPThetaController, 0, 0, Constants.AutoConstants.kThetaControllerConstraints);
        thetaController.enableContinuousInput(-Math.PI, Math.PI);

        SwerveControllerCommand swerveControllerCommand =
            new SwerveControllerCommand(
                exampleTrajectory,
                s_Swerve::getPose,
                Constants.Swerve.swerveKinematics,
                new PIDController(Constants.AutoConstants.kPXController, 0, 0),
                new PIDController(Constants.AutoConstants.kPYController, 0, 0),
                thetaController,
                s_Swerve::setModuleStates,
                s_Swerve);
                /* 
        SwerveControllerCommand swerveControllerCommand2 =
            new SwerveControllerCommand(
                exampleTrajectory2,
                s_Swerve::getPose,
                Constants.Swerve.swerveKinematics,
                new PIDController(Constants.AutoConstants.kPXController, 0, 0),
                new PIDController(Constants.AutoConstants.kPYController, 0, 0),
                thetaController,
                s_Swerve::setModuleStates,
                s_Swerve);
                */


        addCommands(
            new InstantCommand(() -> s_Swerve.setPose(exampleTrajectory.getInitialPose())),
            swerveControllerCommand);
            /*
            new InstantCommand(() -> s_Swerve.setPose(exampleTrajectory2.getInitialPose())), 
            swerveControllerCommand2); 
            */
    }
}

