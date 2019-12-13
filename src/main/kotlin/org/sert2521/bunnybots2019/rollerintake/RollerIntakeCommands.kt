package org.sert2521.bunnybots2019.rollerintake

import org.sert2521.sertain.events.onTick
import org.sert2521.sertain.subsystems.doTask
import org.sert2521.sertain.subsystems.use

suspend fun roll() = doTask {
    val roller = use<RollerIntake>()
    action {
        val job = onTick {
            roller.spinIntake()
        }
    }
}