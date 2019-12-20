package org.sert2521.bunnybots2019.beddumper

import kotlinx.coroutines.launch
import org.sert2521.sertain.coroutines.periodic
import org.sert2521.sertain.events.onTick
import org.sert2521.sertain.subsystems.doTask
import org.sert2521.sertain.subsystems.use

suspend fun dumpBed() = doTask {
    val beddumper = use<Beddumper>()
    action {
        try {
            periodic(20) {
                beddumper.spinBedDumper()
                println("Spinning Bed Dumper")
            }
        } finally {
            println("Bed Dumper should stop")
            beddumper.stopSpin()
        }
    }
}

