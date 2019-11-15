package org.sert2521.bunnybots2019.drivetrain

import edu.wpi.first.wpilibj.DriverStation
import org.sert2521.sertain.motors.MotorController
import org.sert2521.sertain.subsystems.Subsystem

class Drivetrain : Subsystem("Drivetrain") {
    val rightFront = MotorController(org.sert2521.bunnybots2019.rightFront)
    val rightBack = MotorController(org.sert2521.bunnybots2019.rightBack)
    val leftFront = MotorController(org.sert2521.bunnybots2019.leftFront)
    val leftBack = MotorController(org.sert2521.bunnybots2019.leftBack)
}

//val ds =  DriverStation.getInstance().
