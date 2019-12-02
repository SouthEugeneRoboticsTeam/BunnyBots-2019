package org.sert2521.bunnybots2019

import kotlinx.coroutines.launch
import org.sert2521.bunnybots2019.drivetrain.Drivetrain
import org.sert2521.sertain.events.onEnable
import org.sert2521.sertain.events.onTeleop
import org.sert2521.sertain.robot
import org.sert2521.sertain.subsystems.doTask

fun main() = robot {
    add<Drivetrain>()

    onTeleop {
        doTask {
            val dt = use<Drivetrain>()
            action {
                dt.arcadeDrive(.5, 0.0)
            }
        }
    }

}
