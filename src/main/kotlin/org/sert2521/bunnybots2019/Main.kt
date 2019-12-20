package org.sert2521.bunnybots2019

import org.sert2521.bunnybots2019.cubeintake.CubeIntake
import org.sert2521.bunnybots2019.drivetrain.Drivetrain
import org.sert2521.bunnybots2019.oi.getInputs
import org.sert2521.bunnybots2019.tubintake.TubIntake
import org.sert2521.bunnybots2019.tubintake.teleopIntakeControl
import org.sert2521.sertain.events.whileTeleop
import org.sert2521.sertain.robot
import org.sert2521.sertain.subsystems.add

suspend fun main() = robot {
    add<Drivetrain>()
    add<TubIntake>()
    add<CubeIntake>()

    whileTeleop {
        teleopIntakeControl()
        getInputs()
    }
}
