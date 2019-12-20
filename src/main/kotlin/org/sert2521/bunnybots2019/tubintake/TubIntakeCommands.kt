package org.sert2521.bunnybots2019.tubintake

import org.sert2521.bunnybots2019.oi.Controls
import org.sert2521.sertain.coroutines.periodic
import org.sert2521.sertain.events.onTick
import org.sert2521.sertain.subsystems.doTask
import org.sert2521.sertain.subsystems.use
import kotlin.math.abs

suspend fun tubIntake() = doTask {
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

suspend fun tubOuttake() = doTask {
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

suspend fun teleopIntakeControl() = doTask {
    val intake = use<TubIntake>()
    action {
        onTick {
            //            if (Controls.tubintakeInButton) {
//                println("Intake should be spinning in")
//                intake.spinIntake()
//            } else if (Controls.tubintakeOutButton) {
//                println("Intake should be spinning out")
//                intake.spinOuttake()
//            } else {
//                println("Stopping spin")
//                intake.stopSpin()
//            }
            if(!intake.armRunning && abs(intake.position) < 10) {
                //thanks, i hate it
                intake.home()
            }
            if (Controls.tubintakeArmUpButton && !intake.armRunning) {
                println("Going up")
                intake.runArmToPosition(ARM_UP_TICKS)
            }

            if (Controls.tubintakeArmDownButton && !intake.armRunning) {
                println("Going down")
                intake.runArmToPosition(ARM_DOWN_TICKS)
            }
        }
    }
}

suspend fun autoRunArm(position: Int) = doTask {
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