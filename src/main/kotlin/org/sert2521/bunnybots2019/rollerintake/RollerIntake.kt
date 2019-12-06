package org.sert2521.bunnybots2019.rollerintake

import org.sert2521.bunnybots2019.MotorControllers
import org.sert2521.sertain.motors.MotorController
import org.sert2521.sertain.motors.NeutralMode
import org.sert2521.sertain.subsystems.Subsystem

class RollerIntake : Subsystem("Roller") {
    private val brake = NeutralMode.BRAKE

    private val roller = MotorController(
            MotorControllers.ROLLER_LEFT,
            MotorControllers.ROLLER_RIGHT
    ) {
        eachFollower {
            inverted = false
            brakeMode = true

            maxOutputRange = -0.75..0.75

        }
    }

    private var intakeRunning = false

    fun spinIntake () {
        roller.setPercentOutput (ROLLERINTAKE_SPEED)
        intakeRunning = true
    }

    fun stopSpin() {
        roller.disable()
        intakeRunning = false
        }
}