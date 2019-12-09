package org.sert2521.bunnybots2019

import kotlinx.coroutines.delay
import org.sert2521.bunnybots2019.drivetrain.Drivetrain
import org.sert2521.bunnybots2019.oi.WatashiNoTable
import org.sert2521.sertain.events.onEnable
import org.sert2521.sertain.robot
import org.sert2521.sertain.subsystems.add

suspend fun main() = robot {
    add<Drivetrain>()
    onEnable {
        WatashiNoTable.x
        delay(1000)
        WatashiNoTable.x = 4
        delay(10000)
        println(WatashiNoTable.x)
    }
}
