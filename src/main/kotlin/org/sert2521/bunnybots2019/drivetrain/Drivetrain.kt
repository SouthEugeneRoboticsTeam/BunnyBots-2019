package org.sert2521.bunnybots2019.drivetrain

import org.sert2521.bunnybots2019.leftBack
import org.sert2521.bunnybots2019.leftFront
import org.sert2521.bunnybots2019.rightBack
import org.sert2521.bunnybots2019.rightFront
import org.sert2521.sertain.motors.MotorController
import org.sert2521.sertain.subsystems.Subsystem

class Drivetrain : Subsystem("Drivetrain", ::controlDrivetrain) {
    private val right = MotorController(rightFront, rightBack) {
        inverted = true
        brakeMode = true
    }
    private val left = MotorController(leftFront, leftBack) {
        brakeMode = true
    }

    init {
        right.position = 0
        left.position = 0
    }

    fun arcadeDrive(speed: Double, turn: Double) {
        right.setPercentOutput(speed - turn)
        left.setPercentOutput(speed + turn)
    }

    fun tankDrive(rightSpeed: Double, leftSpeed: Double) {
        right.setPercentOutput(rightSpeed)
        left.setPercentOutput(leftSpeed)
    }

}
