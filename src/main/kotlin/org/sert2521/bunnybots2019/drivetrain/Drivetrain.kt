package org.sert2521.bunnybots2019.drivetrain

import edu.wpi.first.wpilibj.DriverStation
import kotlinx.coroutines.launch
import org.sert2521.sertain.motors.MotorController
import org.sert2521.sertain.subsystems.Subsystem
import org.sert2521.bunnybots2019.leftFront
import org.sert2521.bunnybots2019.leftBack
import org.sert2521.bunnybots2019.rightFront
import org.sert2521.bunnybots2019.rightBack
import org.sert2521.sertain.Robot
import org.sert2521.bunnybots2019.drivetrain.driveTrain

class Drivetrain : Subsystem("Drive Train") {
    private val right = MotorController(rightFront, rightBack)
    private val left = MotorController(leftFront, leftBack)

    init{
        default {
            Robot.driveTrain()
        }
    }

    fun arcadeDrive(speed: Double, turn: Double){
        right.setPercentOutput(speed - turn)
        left.setPercentOutput(speed + turn)
    }

    fun tankDrive(rightSpeed: Double, leftSpeed: Double){
        right.setPercentOutput(rightSpeed)
        left.setPercentOutput(leftSpeed)
    }
}
