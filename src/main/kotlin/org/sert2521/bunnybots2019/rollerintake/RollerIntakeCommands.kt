package org.sert2521.bunnybots2019.rollerintake

import org.sert2521.sertain.Robot
import org.sert2521.sertain.subsystems.doTask
import org.sert2521.sertain.subsystems.use

suspend fun Robot.roll() = doTask {
    val roller = use<RollerIntake>()
    action {

    }
}