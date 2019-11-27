package org.sert2521.bunnybots2019.tubintake

import org.sert2521.sertain.Robot
import org.sert2521.sertain.robot
import org.sert2521.sertain.subsystems.doTask

fun main() = robot {
    add<TubIntake>()
}

suspend fun Robot.tunIntakeControl() = doTask {
    val intake = use<TubIntake>()
    action {

    }
}