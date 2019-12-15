package org.sert2521.bunnybots2019.drivetrain

import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.XboxController
import org.sert2521.bunnybots2019.oi.controlMode
import org.sert2521.sertain.events.onTick
import org.sert2521.sertain.subsystems.doTask
import org.sert2521.sertain.subsystems.use
import kotlin.math.sign

typealias DoubleRange = ClosedFloatingPointRange<Double>

fun Double.deadband(range: Double): Double {
    return if (this < range && this > -range) {
        0.0
    } else {
        this
    }
}

fun DoubleRange.intersects(other: DoubleRange): Boolean =
        start in other || endInclusive in other

fun Number.remap(fromRange: DoubleRange, toRange: DoubleRange) =
        (this.toDouble() - fromRange.start) * (toRange.endInclusive - toRange.start) / (fromRange.endInclusive - fromRange.start) + toRange.start

val controller = XboxController(1)

private val throttle get() = controller.getY(GenericHID.Hand.kLeft)
private val turn get() = controller.getX(GenericHID.Hand.kRight)
private val scale = 0.75

suspend fun driveTrain() = doTask {
    val drivetrain = use<Drivetrain>()
    println("I'm here")
    action {
        val job = onTick {

            throttle.deadband(0.05)
            turn.deadband(0.05)
            println("ControlMode ${controlMode}")
            val scaledThrottle = -throttle.sign * (throttle * throttle * scale)
            val scaledTurn = turn.sign * (turn * turn * scale)
            drivetrain.arcadeDrive(scaledThrottle, scaledTurn)
            println(scaledThrottle)
            println(scaledTurn)
            println(turn)
            println(throttle)
        }
    }
}
