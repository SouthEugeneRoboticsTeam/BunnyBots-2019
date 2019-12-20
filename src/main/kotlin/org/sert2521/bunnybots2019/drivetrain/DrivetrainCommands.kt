package org.sert2521.bunnybots2019.drivetrain

import edu.wpi.first.wpilibj.GenericHID
import kotlinx.coroutines.cancel
import org.sert2521.bunnybots2019.oi.primaryJoystick
import org.sert2521.bunnybots2019.utils.deadband
import org.sert2521.bunnybots2019.oi.ControlMode
import org.sert2521.bunnybots2019.oi.controlMode
import org.sert2521.bunnybots2019.oi.primaryController
import org.sert2521.sertain.events.onTick
import org.sert2521.sertain.subsystems.doTask
import org.sert2521.sertain.subsystems.use
import kotlin.math.sign


private val throttle
    get() = when (controlMode) {
        ControlMode.CONTROLLER -> primaryController.getY(GenericHID.Hand.kLeft)
        ControlMode.JOYSTICK -> primaryJoystick.y.deadband(0.02)
    }
private val turn
    get() = when (controlMode) {
        ControlMode.CONTROLLER -> primaryController.getX(GenericHID.Hand.kRight)
        ControlMode.JOYSTICK -> primaryJoystick.x.deadband(0.02)
    }

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

suspend fun driveDistanceWithSetPosition(speed: Double, distance: Double) = doTask {
    val drivetrain = use<Drivetrain>()
    val ticks = ((PULSES_PER_REVOLUTION * distance / 2.0) / (2 * Math.PI * WHEEL_RADIUS)).toInt()
    action {
        onTick {
            drivetrain.driveToPosition(ticks)
        }
    }
}
