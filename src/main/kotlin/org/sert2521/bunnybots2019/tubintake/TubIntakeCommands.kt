package org.sert2521.bunnybots2019.tubintake

import org.sert2521.bunnybots2019.oi.Controls
import org.sert2521.sertain.coroutines.periodic
import org.sert2521.sertain.events.onTick
import org.sert2521.sertain.subsystems.doTask
import org.sert2521.sertain.subsystems.use
import kotlin.math.abs

suspend fun intakeTub() = doTask {
    val tubIntake = use<TubIntake>()
    action {
        try {
            periodic(20) {
                tubIntake.spinIntake()
                println("Spinning tub intake")
            }
        } finally {
            tubIntake.stopSpin()
            println("Stopping tub intake spin")
        }
    }
}

suspend fun outtakeTub() = doTask {
    val tubIntake = use<TubIntake>()
    action {
        try {
            periodic(20) {
                tubIntake.spinOuttake()
                println("Spinning tub intake")
            }
        } finally {
            tubIntake.stopSpin()
            println("Stopping tub intake spin")
        }
    }
}

suspend fun homeArm() = doTask {
    val intake = use<TubIntake>()
    action {
        onTick {
            if (!intake.armRunning && abs(intake.position) < 10) {
                //thanks, i hate it
                intake.home()
            }
        }
    }
}

suspend fun runArmTo(position: Int) = doTask {
    val intake = use<TubIntake>()
    action {
        intake.runArmToPosition(position)
    }
}

suspend fun autoSpinIntake(enabled: Boolean, intaking: Boolean) = doTask {
    val intake = use<TubIntake>()
    action {
        if (!enabled) {
            intake.stopSpin()
        } else {
            onTick {
                if (intaking) {
                    intake.spinIntake()
                } else {
                    intake.spinOuttake()
                }
            }
        }
    }
}
