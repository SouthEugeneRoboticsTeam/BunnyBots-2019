package org.sert2521.bunnybots2019

import org.sert2521.sertain.motors.TalonId
import org.sert2521.sertain.motors.VictorId

object MotorControllers {
    val leftFront = TalonId(3)
    val leftBack = TalonId(4)
    val rightFront = TalonId(2)
    val rightBack = TalonId(1)

    val ROLLER = TalonId(5)

    val TUBINTAKE_WHEEL_LEFT = TalonId(8)
    val TUBINTAKE_WHEEL_RIGHT = VictorId(9)

    val TUBINTAKE_ARM_A = TalonId(7)
    val TUBINTAKE_ARM_B = TalonId(6)

    val BEDDUMPER = VictorId(20)
}

object Sensors {
    val TUBINTAKE_LIMIT_TOP = 0
}

object Operator {
    val PRIMARY_CONTROLLER = 0
    val PRIMARY_STICK = 1
    val SECONDARY_STICK = 2

    val TUBINTAKE_IN_BUTTON = 3
    val TUBINTAKE_OUT_BUTTON = 4
    val TUBINTAKE_ARM_UP_BUTTON = 8
    val TUBINTAKE_ARM_DOWN_BUTTON = 9

    val CUBEINTAKE_BUTTON = 1
    val CUBEOUTTAKE_BUTTON = 13

    val BEDDUMP_BUTTON = 14
}
