package org.sert2521.bunnybots2019.drivetrain

import edu.wpi.first.wpilibj.GenericHID
import kotlinx.coroutines.cancel
import org.sert2521.bunnybots2019.oi.primaryJoystick
import org.sert2521.bunnybots2019.utils.deadband
import org.sert2521.bunnybots2019.oi.ControlMode
import org.sert2521.bunnybots2019.oi.controlMode
import org.sert2521.bunnybots2019.oi.primaryController
import org.sert2521.sertain.control.MotionCurve
import org.sert2521.sertain.events.onTick
import org.sert2521.sertain.motors.EncoderTicks
import org.sert2521.sertain.subsystems.doTask
import org.sert2521.sertain.subsystems.use
import org.sert2521.sertain.units.Angular
import org.sert2521.sertain.units.Chronic
import org.sert2521.sertain.units.CompositeUnit
import org.sert2521.sertain.units.CompositeUnitType
import org.sert2521.sertain.units.Linear
import org.sert2521.sertain.units.LinearUnit
import org.sert2521.sertain.units.Meters
import org.sert2521.sertain.units.MetricUnit
import org.sert2521.sertain.units.MetricValue
import org.sert2521.sertain.units.Per
import org.sert2521.sertain.units.Radians
import org.sert2521.sertain.units.Seconds
import org.sert2521.sertain.units.convertTo
import org.sert2521.sertain.units.div
import org.sert2521.sertain.utils.timer
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
suspend fun driveCurve(speed: Double, distance: Double) = doTask {
    val drivetrain = use<Drivetrain>()
    action {
        drivetrain.zeroEncoders()
        // Calculates the number of encoder pulses corresponding to a certain distance
//        val ticks = (PULSES_PER_REVOLUTION * distance) / (2 * Math.PI * WHEEL_RADIUS)
        val ticks = PULSES_PER_REVOLUTION
        println("ticks: ${ticks}")

        onTick {
            if (drivetrain.position >= ticks) {
                drivetrain.stop()
                this@action.cancel()
            } else {
                val distancePerSecond = speed
                drivetrain.tankDrive(speed, speed)

            }

        }
    }
}

suspend fun <VU : CompositeUnit<Per, Linear, Chronic>, DU : MetricUnit<Linear>> driveCurve(
        speed: MetricValue<CompositeUnitType<Per, Linear, Chronic>, VU>,
        distance: MetricValue<Linear, DU>
) = doTask("") {
    println("Calling driveCurve")
    val drivetrain = use<Drivetrain>()
    action {
        println("Driving curve")
        drivetrain.zeroEncoders()

        val ticks = MetricValue(
                Radians,
                distance.convertTo(Meters).value / WHEEL_RADIUS_METERS
        ).convertTo(EncoderTicks(PULSES_PER_REVOLUTION)).value
        val ticksPerSecond = MetricValue(
                Radians / Seconds,
                speed.convertTo(Meters / Seconds).value / WHEEL_RADIUS_METERS
        ).convertTo(EncoderTicks(PULSES_PER_REVOLUTION) / Seconds).value
        val curve = MotionCurve(ticks, ticksPerSecond, 50000.0, 10000.0)

        timer(20, 0, (curve.t7 * 1000).toLong()) {
            println(curve.d(it / 1000.0))
            drivetrain.driveToPosition(curve.d(it / 1000.0).toInt())
            println("Right pos: ${drivetrain.rightPosition}")
            println("Left pos: ${drivetrain.leftPosition}")
        }
    }
}
