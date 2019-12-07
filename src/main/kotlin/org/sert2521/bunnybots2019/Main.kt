package org.sert2521.bunnybots2019

import org.sert2521.bunnybots2019.tubintake.teleopIntakeControl
import org.sert2521.sertain.Robot
import org.sert2521.sertain.events.onTeleop
import org.sert2521.sertain.robot

fun main() = robot {
    onTeleop {
        Robot.teleopIntakeControl()
    }
}
