package org.sert2521.bunnybots2019.intake

import org.sert2521.sertain.events.onTick
import org.sert2521.sertain.subsystems.doTask
import org.sert2521.sertain.subsystems.use

suspend fun intake() = doTask {
    val intake = use<Intake>()
    action {
        val job = onTick {
            intake.spinIntake()
        }
    }
}