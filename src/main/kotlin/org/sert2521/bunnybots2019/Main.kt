package org.sert2521.bunnybots2019

import org.sert2521.bunnybots2019.tubintake.*
import org.sert2521.sertain.Robot
import org.sert2521.sertain.events.onTeleop
import org.sert2521.sertain.robot
import org.sert2521.sertain.subsystems.add

suspend fun main() = robot {
    onTeleop {
        add<TubIntake>()
        Robot.teleopIntakeControl()
    }
}
