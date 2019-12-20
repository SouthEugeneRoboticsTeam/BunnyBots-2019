package org.sert2521.bunnybots2019.cubeintake

import org.sert2521.bunnybots2019.MotorControllers
import org.sert2521.sertain.motors.MotorController
import org.sert2521.sertain.subsystems.Subsystem

class CubeIntake : Subsystem("CubeIntake") {
    private val intakeMotor = MotorController(
            MotorControllers.ROLLER
    ) {
        inverted = true
        brakeMode = true
        maxOutputRange = -0.75..0.75
    }

    private var intakeRunning = false

    fun spinIntake() {
        intakeMotor.setPercentOutput(ROLLER_INTAKE_SPEED)
        intakeRunning = true
    }

    fun spinReverse() {
        intakeMotor.setPercentOutput(-ROLLER_INTAKE_SPEED)
        intakeRunning = true
    }

    fun stopSpin() {
        intakeMotor.disable()
        intakeRunning = false
    }
}
