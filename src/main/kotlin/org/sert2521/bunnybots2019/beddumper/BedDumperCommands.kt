package org.sert2521.bunnybots2019.beddumper

import kotlinx.coroutines.launch
import org.sert2521.sertain.coroutines.periodic
import org.sert2521.sertain.events.onTick
import org.sert2521.sertain.subsystems.doTask
import org.sert2521.sertain.subsystems.use

suspend fun beddumper() = doTask {
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

//suspend fun reverseIntake() = doTask {
//    val intake = use<Intake>()
//    action {
//        try {
//            periodic(20) {
//                intake.spinReverse()
//                println("Spinning intake")
//            }
//        } finally {
//            println("Intake should stop")
//            intake.stopSpin()
//        }
//    }
//}