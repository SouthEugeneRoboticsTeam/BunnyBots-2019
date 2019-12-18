package org.sert2521.bunnybots2019.beddumper;

import org.sert2521.bunnybots2019.MotorControllers
import org.sert2521.sertain.motors.MotorController
import org.sert2521.sertain.subsystems.Subsystem

class Beddumper : Subsystem("Beddumper") {
        private val intakeMotor = MotorController(
                MotorControllers.BEDDUMPER
        ) {
                inverted = true
                brakeMode = true
        }

        private var intakeRunning = false

        fun spinBedDumper() {
                intakeMotor.setPercentOutput(BED_DUMPER_SPEED)
                intakeRunning = true
        }

//        fun spinReverse() {
//                intakeMotor.setPercentOutput(-BED_DUMPER_SPEED)
//                intakeRunning = true
//        }

        fun stopSpin() {
                intakeMotor.disable()
                intakeRunning = false
        }
}