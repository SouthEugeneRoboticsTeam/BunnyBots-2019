package org.sert2521.bunnybots2019.drivetrain

import kotlinx.coroutines.cancelAndJoin
import org.sert2521.bunnybots2019.drivetrain.Drivetrain
import org.sert2521.sertain.Robot
import org.sert2521.sertain.coroutines.delayUntil
import org.sert2521.sertain.events.onTick
import org.sert2521.sertain.subsystems.doTask

suspend fun Robot.dumpBed(speed: Double) = doTask {
    val bedDumper = use<Drivetrain>()
    action {
        val job = onTick {

        }
    }
}
