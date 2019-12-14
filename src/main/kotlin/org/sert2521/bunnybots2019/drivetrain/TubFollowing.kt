package org.sert2521.bunnybots2019.drivetrain

import edu.wpi.first.networktables.NetworkTableInstance
import kotlin.math.pow

private suspend fun pickUpTub (drivetrain: Drivetrain, turnSpeed: Double, activationNum: Double, distanceToSlow: Double){
    var table = NetworkTableInstance.getDefault().getTable("Vision")

    var distance = table.getEntry("distance").toString().toDouble()
    var xAngle = table.getEntry("x_angle_from_center").toString().toDouble()

    if(distance > distanceToSlow) {
        drivetrain.arcadeDrive(1.0, xAngle.deadband(activationNum).pow(2) * turnSpeed.pow(2))
    }else{
        drivetrain.arcadeDrive(0.25, xAngle.deadband(activationNum) * turnSpeed.pow(2))
    }
}
