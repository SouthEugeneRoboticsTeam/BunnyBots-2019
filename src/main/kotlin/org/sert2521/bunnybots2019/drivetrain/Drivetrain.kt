package org.sert2521.bunnybots2019.drivetrain

import kotlinx.coroutines.launch
import org.sert2521.bunnybots2019.leftBack
import org.sert2521.bunnybots2019.leftFront
import org.sert2521.bunnybots2019.oi.primaryJoystick
import org.sert2521.bunnybots2019.rightBack
import org.sert2521.bunnybots2019.rightFront
import org.sert2521.sertain.coroutines.RobotScope
import org.sert2521.sertain.coroutines.watch
import org.sert2521.sertain.motors.MotorController
import org.sert2521.sertain.subsystems.Subsystem
import kotlin.math.abs

class Drivetrain : Subsystem("Drivetrain", ::driveTrain) {
    private val right = MotorController(rightFront, rightBack) {
        inverted = true
        brakeMode = true
        sensorInverted = true
    }
    private val left = MotorController(leftFront, leftBack) {
        brakeMode = true
        sensorInverted = true
    }

    val rightPosition get() = right.position
    val leftPosition get() = left.position
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
        right.position = 0
        left.position = 0
    }

}