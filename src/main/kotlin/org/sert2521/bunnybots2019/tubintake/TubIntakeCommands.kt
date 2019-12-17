package org.sert2521.bunnybots2019.tubintake

import kotlinx.coroutines.launch
import org.sert2521.bunnybots2019.oi.Controls
import org.sert2521.bunnybots2019.utils.timer
import org.sert2521.sertain.coroutines.RobotScope
import org.sert2521.sertain.events.onTick
import org.sert2521.sertain.subsystems.doTask
import org.sert2521.sertain.subsystems.use

suspend fun teleopIntakeControl() = doTask {
    val intake = use<TubIntake>()
    action {
        val job = onTick {
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

suspend fun autoIntakeCommand(position: Int, intakeRunning: Boolean) = doTask {
    val intake = use<TubIntake>()
    action {
        if (intakeRunning) {
            RobotScope.launch {
                timer(20, 3000) {
                    intake.spinIntake()
                }
            }
        }
        intake.runArmToPosition(position)
    }
}
