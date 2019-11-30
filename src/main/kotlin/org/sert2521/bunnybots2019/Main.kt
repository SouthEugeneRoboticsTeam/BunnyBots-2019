package org.sert2521.bunnybots2019

import kotlinx.coroutines.launch
import org.sert2521.bunnybots2019.drivetrain.Drivetrain
import org.sert2521.sertain.robot

fun main() = robot {
    add<Drivetrain>()
}
