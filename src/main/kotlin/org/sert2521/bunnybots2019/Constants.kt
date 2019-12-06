package org.sert2521.bunnybots2019

import org.sert2521.sertain.motors.TalonId
import org.sert2521.sertain.motors.VictorId

// Drivetrain motor ids
val leftFront = TalonId(1)
val leftBack = TalonId(2)
val rightFront = TalonId(3)
val rightBack = TalonId(4)

// Operator constants
const val PRIMARY_CONTROLLER = 0
const val PRIMARY_STICK = 2
const val SECONDARY_STICK = 1


object MotorControllers {
    val ROLLER_RIGHT = TalonId(-1)
    val ROLLER_LEFT = TalonId(-1)
}
