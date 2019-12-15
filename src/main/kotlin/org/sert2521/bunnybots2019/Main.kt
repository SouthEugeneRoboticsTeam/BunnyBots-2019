package org.sert2521.bunnybots2019

import edu.wpi.first.wpilibj.DigitalInput
import kotlinx.coroutines.delay
import org.sert2521.bunnybots2019.tubintake.*
import org.sert2521.sertain.Robot
import org.sert2521.sertain.coroutines.delayForever
import org.sert2521.sertain.events.onAuto
import org.sert2521.sertain.events.onTeleop
import org.sert2521.sertain.events.onTick
import org.sert2521.sertain.robot
import org.sert2521.sertain.subsystems.add

suspend fun main() = robot {
    add<TubIntake>()
    onTeleop {
        teleopIntakeControl()
    }

    onAuto {
    }
}
