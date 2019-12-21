package org.sert2521.bunnybots2019.beddumper

import org.sert2521.sertain.events.onTick
import org.sert2521.sertain.subsystems.doTask
import org.sert2521.sertain.subsystems.use

suspend fun dumpBed() = doTask {
    val beddumper = use<BedDumper>()
    action {
        try {
            beddumper.setServoAngle(30.0)
            onTick {
                beddumper.spinBedDumper()
                println("Spinning Bed Dumper")
            }.join()
        } finally {
            println("Bed Dumper should stop")
            beddumper.stopSpin()
            beddumper.setServoAngle(360.0)
        }
    }
}

