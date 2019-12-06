package org.sert2521.bunnybots2019.drivetrain

import org.sert2521.bunnybots2019.oi.primaryJoystick
import org.sert2521.sertain.events.onTick
import org.sert2521.sertain.subsystems.doTask
import org.sert2521.sertain.subsystems.use
import kotlin.math.sign

typealias DoubleRange = ClosedFloatingPointRange<Double>

fun Double.deadband(range: Double): Double{
    return if(this < range && this > -range) {
        0.0
    }else{
        this
    }
}

fun DoubleRange.intersects(other: DoubleRange): Boolean =
        start in other || endInclusive in other

fun Number.remap(fromRange: DoubleRange, toRange: DoubleRange) =
        (this.toDouble() - fromRange.start) * (toRange.endInclusive - toRange.start) / (fromRange.endInclusive - fromRange.start) + toRange.start


private val throttle get() = primaryJoystick.y
private val turn get() = primaryJoystick.x
private val scale get() = 1.0

suspend fun driveTrain() = doTask {
    val drivetrain = use<Drivetrain>()
    action {
        val job = onTick {

            throttle.deadband(0.05)
            turn.deadband(0.05)

            val scaledThrottle = -throttle.sign * (throttle * throttle)
            val scaledTurn = turn.sign * (turn  * turn)
            drivetrain.arcadeDrive(scaledThrottle, scaledTurn)
        }
    }
}