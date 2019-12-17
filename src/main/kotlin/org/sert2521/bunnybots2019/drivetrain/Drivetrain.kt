package org.sert2521.bunnybots2019.drivetrain

import org.sert2521.bunnybots2019.*
import org.sert2521.sertain.motors.MotorController
import org.sert2521.sertain.subsystems.Subsystem

class Drivetrain : Subsystem("Drivetrain", ::controlDrivetrain) {
    private val right = MotorController(MotorControllers.rightFront, MotorControllers.rightBack) {
        inverted = true
        brakeMode = true
        sensorInverted = true
    }
    private val left = MotorController(MotorControllers.leftFront, MotorControllers.leftBack) {
        brakeMode = true
        sensorInverted = true
    }

    val rightPosition get() = right.sensorPosition
    val leftPosition get() = left.sensorPosition
    val position get() = (rightPosition + leftPosition) / 2


    init {
        zeroEncoders()
    }

    fun arcadeDrive(speed: Double, turn: Double) {
        right.setPercentOutput(speed - turn)
        left.setPercentOutput(speed + turn)
    }

    fun tankDrive(rightSpeed: Double, leftSpeed: Double) {
        right.setPercentOutput(rightSpeed)
        left.setPercentOutput(leftSpeed)
    }

    fun stop() {
        right.setPercentOutput(0.0)
        left.setPercentOutput(0.0)
    }

    fun zeroEncoders() {
        right.sensorPosition = 0
        left.sensorPosition = 0
    }

}
