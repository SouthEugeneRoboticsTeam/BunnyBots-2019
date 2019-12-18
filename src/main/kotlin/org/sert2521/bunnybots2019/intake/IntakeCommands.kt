package org.sert2521.bunnybots2019.intake

import kotlinx.coroutines.launch
import org.sert2521.sertain.coroutines.periodic
import org.sert2521.sertain.events.onTick
import org.sert2521.sertain.subsystems.doTask
import org.sert2521.sertain.subsystems.use

suspend fun intake() = doTask {
    val intake = use<Intake>()
    action {
        try {
            periodic(20) {
                intake.spinIntake()
                println("Spinning intake")
            }
        } finally {
            println("Intake should stop")
            intake.stopSpin()
        }
    }
}
