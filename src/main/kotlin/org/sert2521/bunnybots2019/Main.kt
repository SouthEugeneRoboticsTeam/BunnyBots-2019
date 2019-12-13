package org.sert2521.bunnybots2019

import org.sert2521.bunnybots2019.drivetrain.Drivetrain
import org.sert2521.bunnybots2019.oi.joystickInputs
import org.sert2521.bunnybots2019.rollerintake.RollerIntake
import org.sert2521.sertain.robot
import org.sert2521.sertain.subsystems.add

suspend fun main() = robot {
    add<Drivetrain>()
    add<RollerIntake>()

    joystickInputs()
}
