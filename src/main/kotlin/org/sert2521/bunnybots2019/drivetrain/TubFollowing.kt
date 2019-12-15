package org.sert2521.bunnybots2019.drivetrain

import com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table
import edu.wpi.first.networktables.NetworkTableInstance
import kotlinx.coroutines.cancel
import org.sert2521.sertain.events.onTick
import org.sert2521.sertain.subsystems.doTask
import org.sert2521.sertain.subsystems.use
import java.time.Instant
import kotlin.math.pow

suspend fun pickUpTub(turnSpeed: Double, activationNum: Double, distanceToSlow: Double, distanceToFinish: Double) = doTask {
    val drivetrain = use<Drivetrain>()

    action {
        onTick {
            var table = NetworkTableInstance.getDefault().getTable("Vision")

            var distance = table.getEntry("distance").getDouble(200.0)
            var xAngle = table.getEntry("x_angle_from_center").getDouble(0.0)
            var lastAlive = table.getEntry("x_angle_from_center").getDouble(0.0)

            if (lastAlive - (Instant.now().toEpochMilli() / 1000.0) < 1.0) {
                when {
                    distance > distanceToSlow -> drivetrain.arcadeDrive(0.1, (xAngle.deadband(activationNum) * turnSpeed).pow(3))
                    distanceToSlow >= distance && distance > distanceToFinish -> drivetrain.arcadeDrive(0.02, (xAngle.deadband(activationNum) * turnSpeed).pow(3))
                    else -> {
                        this@action.cancel()
                    }
                }
            }
        }
    }
}
