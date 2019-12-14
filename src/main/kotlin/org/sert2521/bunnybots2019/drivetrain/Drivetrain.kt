package org.sert2521.bunnybots2019.drivetrain

import org.sert2521.bunnybots2019.*
import org.sert2521.sertain.motors.MotorController
import org.sert2521.sertain.subsystems.Subsystem

class Drivetrain : Subsystem("Drivetrain", ::driveTrain) {
    private val right = MotorController(MotorControllers.rightFront, MotorControllers.rightBack) {
        inverted = true
        brakeMode = true
    }
    private val left = MotorController(MotorControllers.leftFront, MotorControllers.leftBack) {
        brakeMode = true
    }

    init {
        right.sensorPosition = 0
        left.sensorPosition = 0
    }


    fun arcadeDrive(speed: Double, turn: Double) {
        right.setPercentOutput(speed - turn)
        left.setPercentOutput(speed + turn)
        println("right" + (speed - turn))
        println("left" + (speed + turn))
    }

    fun tankDrive(rightSpeed: Double, leftSpeed: Double) {
        right.setPercentOutput(rightSpeed)
        left.setPercentOutput(leftSpeed)
    }

}
