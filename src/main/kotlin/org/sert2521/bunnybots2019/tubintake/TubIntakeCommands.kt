package org.sert2521.bunnybots2019.tubintake

import org.sert2521.bunnybots2019.oi.Controls
import org.sert2521.sertain.Robot
import org.sert2521.sertain.events.onTick
import org.sert2521.sertain.robot
import org.sert2521.sertain.subsystems.doTask

fun main() = robot {
    add<TubIntake>()
}

suspend fun Robot.teleopIntakeControl() = doTask {
    val intake = use<TubIntake>()
    action {
         val job = onTick {
            if (Controls.tubintakeInButton == true) {
                intake.spinIntake()
            } else if (Controls.tubintakeOutButton == true) {
                intake.spinOutake()
            } else {
                intake.stopSpin()
            }
         }
    }
}