package org.sert2521.bunnybots2019.drivetrain

import edu.wpi.first.networktables.NetworkTableInstance
import kotlinx.coroutines.cancel
import org.sert2521.sertain.events.onTick
import org.sert2521.sertain.subsystems.doTask
import org.sert2521.sertain.subsystems.use
import kotlin.math.pow

suspend fun pickUpTub (turnSpeed: Double, activationNum: Double, distanceToSlow: Double, distanceToFinish: Double) = doTask {
    var drivetrain = use<Drivetrain>()

    action {
        val job = onTick {

            var table = NetworkTableInstance.getDefault().getTable("Vision")

            var distance = table.getEntry("distance").toString().toDouble()
            var xAngle = table.getEntry("x_angle_from_center").toString().toDouble()

            when {
                distance > distanceToSlow -> drivetrain.arcadeDrive(1.0, xAngle.deadband(activationNum).pow(2) * turnSpeed.pow(2))
                distanceToSlow >= distance && distance > distanceToFinish -> drivetrain.arcadeDrive(0.25, xAngle.deadband(activationNum) * turnSpeed.pow(2))
                else -> {
                    this@action.cancel()
                }
            }
        }
    }
}
