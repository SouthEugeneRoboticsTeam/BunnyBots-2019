package org.sert2521.bunnybots2019

import org.sert2521.bunnybots2019.drivetrain.Drivetrain
import org.sert2521.bunnybots2019.oi.getInputs
import org.sert2521.sertain.events.onTeleop
import org.sert2521.sertain.robot
import org.sert2521.sertain.subsystems.add

suspend fun main() = robot {
    add<Drivetrain>()

    onTeleop {
        getInputs()
    }
}
