package org.sert2521.bunnybots2019

import org.sert2521.bunnybots2019.tubintake.*
import org.sert2521.bunnybots2019.drivetrain.Drivetrain
import org.sert2521.sertain.events.whileTeleop
import org.sert2521.sertain.robot
import org.sert2521.sertain.subsystems.add

suspend fun main() = robot {
    add<TubIntake>()
    add<Drivetrain>()

    whileTeleop {
        teleopIntakeControl()
    }
}
