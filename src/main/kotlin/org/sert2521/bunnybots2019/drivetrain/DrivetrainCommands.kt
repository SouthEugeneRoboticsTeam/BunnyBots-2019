package org.sert2521.bunnybots2019.drivetrain

import kotlinx.coroutines.cancel
import org.sert2521.bunnybots2019.oi.primaryJoystick
import org.sert2521.bunnybots2019.utils.deadband
import org.sert2521.sertain.events.onTick
import org.sert2521.sertain.subsystems.doTask
import org.sert2521.sertain.subsystems.use
import kotlin.math.sign

private val throttle get() = primaryJoystick.y
private val turn get() = primaryJoystick.x

suspend fun controlDrivetrain() = doTask {
    val drivetrain = use<Drivetrain>()
    action {
        onTick {
            val scaledThrottle = (-throttle.sign * (throttle * throttle)).deadband(.05)
            val scaledTurn = (turn.sign * (turn * turn)).deadband(.05)
            drivetrain.arcadeDrive(scaledThrottle, scaledTurn)
        }
    }
}

// Drives the robot in a straight line for a certain distance (in inches) at a given speed
suspend fun driveDistance(speed: Double, distance: Double) = doTask {
    val drivetrain = use<Drivetrain>()
    action {
        drivetrain.zeroEncoders()
        onTick {
            drivetrain.arcadeDrive(speed, 0.0)
            // Calculates the number of encoder pulses corresponding to a certain distance
            val ticks = (PULSES_PER_REVOLUTION * distance / 2.0) / (2 * Math.PI * WHEEL_RADIUS)
            if (drivetrain.position >= ticks) {
                drivetrain.stop()
                this@action.cancel()
            }
        }
    }
}
