package org.sert2521.bunnybots2019.cubeintake

import org.sert2521.sertain.coroutines.periodic
import org.sert2521.sertain.subsystems.doTask
import org.sert2521.sertain.subsystems.use

suspend fun intakeCubes() = doTask {
    val intake = use<CubeIntake>()
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

suspend fun outtakeCubes() = doTask {
    val intake = use<CubeIntake>()
    action {
        try {
            periodic(20) {
                intake.spinReverse()
                println("Spinning intake")
            }
        } finally {
            println("Intake should stop")
            intake.stopSpin()
        }
    }
}
