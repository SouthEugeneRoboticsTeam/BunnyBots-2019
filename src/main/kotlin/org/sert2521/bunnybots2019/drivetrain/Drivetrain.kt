package org.sert2521.bunnybots2019.drivetrain

import org.sert2521.bunnybots2019.leftBack
import org.sert2521.bunnybots2019.leftFront
import org.sert2521.bunnybots2019.rightBack
import org.sert2521.bunnybots2019.rightFront
import org.sert2521.sertain.motors.MotorController
import org.sert2521.sertain.subsystems.Subsystem

class Drivetrain : Subsystem("Drive Train", ::driveTrain) {
    private val right = MotorController(rightFront, rightBack){
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

/*    fun CoroutineScope.straightDrive(error: Double, speed: Double) {
//        val offset = (right.position - left.position) / 78.74
        onTick {
            val offset = (-right.position - left.position)

            println("Right: ${right.position}")
            println("Left: ${left.position}")


            if (offset !in 0.0..error) {
                println(offset)
                arcadeDrive(speed, -offset * 0.01)
            } else {
                arcadeDrive(speed, 0.0)
            }
        }
    }*/
}
