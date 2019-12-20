package org.sert2521.bunnybots2019.beddumper;

import org.sert2521.bunnybots2019.MotorControllers
import org.sert2521.sertain.motors.MotorController
import org.sert2521.sertain.subsystems.Subsystem

class Beddumper : Subsystem("Beddumper") {
        private val conveyorMotor = MotorController(
                MotorControllers.BEDDUMPER
        ) {
                inverted = true
                brakeMode = true
        }

        private var conveyorRunning = false

        fun spinBedDumper() {
                conveyorMotor.setPercentOutput(BED_DUMPER_SPEED)
                conveyorRunning = true
        }

        fun stopSpin() {
                conveyorMotor.disable()
                conveyorRunning = false
        }
}