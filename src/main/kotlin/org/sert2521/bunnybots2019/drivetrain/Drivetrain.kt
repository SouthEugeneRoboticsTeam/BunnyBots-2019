package org.sert2521.bunnybots2019.drivetrain

import edu.wpi.first.wpilibj.DriverStation
import org.sert2521.bunnybots2019.leftBack
import org.sert2521.sertain.motors.MotorController
import org.sert2521.sertain.subsystems.Subsystem
import org.sert2521.bunnybots2019.leftFront
import org.sert2521.bunnybots2019.rightBack
import org.sert2521.bunnybots2019.rightFront

class Drivetrain : Subsystem("Drivetrain") {
    private val right = MotorController(rightFront, rightBack)
    private val left = MotorController(leftFront, leftBack)

    fun drive(speed: Double, turn: Double){
        right.setPercentOutput(speed - turn)
        left.setPercentOutput(speed + turn)
    }
}
