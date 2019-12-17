package org.sert2521.bunnybots2019.tubintake

import org.sert2521.bunnybots2019.oi.Controls
import org.sert2521.sertain.events.onTick
import org.sert2521.sertain.subsystems.doTask
import org.sert2521.sertain.subsystems.use

suspend fun teleopIntakeControl() = doTask {
    val intake = use<TubIntake>()
    action {
        onTick {
            if (Controls.tubintakeInButton) {
                intake.spinIntake()
            } else if (Controls.tubintakeOutButton) {
                intake.spinOuttake()
            } else {
                intake.stopSpin()
            }

            if (Controls.tubintakeArmUpButton && !intake.armRunning) {
                intake.runArmToPosition(ARM_UP_TICKS)
            }

            if (Controls.tubintakeArmDownButton && !intake.armRunning) {
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
        if(!enabled) {
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