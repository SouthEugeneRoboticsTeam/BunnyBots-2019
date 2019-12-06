package org.sert2521.bunnybots2019.drivetrain

import org.sert2521.bunnybots2019.oi.primaryJoystick
import org.sert2521.sertain.events.onTick
import org.sert2521.sertain.subsystems.doTask
import org.sert2521.sertain.subsystems.use
import kotlin.math.sign

fun Double.deadband(range: Double): Double{
    return if(this < range && this > -range) {
        0.0
    }else{
        this
    }
}

private val throttle get() = primaryJoystick.y
private val turn get() = primaryJoystick.x

suspend fun driveTrain() = doTask {
    val drivetrain = use<Drivetrain>()
    action {
        onTick {
            val scaledThrottle = (-throttle.sign * (throttle * throttle)).deadband(.05)
            val scaledTurn = (turn.sign * (turn * turn)).deadband(.05)
            drivetrain.arcadeDrive(scaledThrottle, scaledTurn)
        }
    }
}
