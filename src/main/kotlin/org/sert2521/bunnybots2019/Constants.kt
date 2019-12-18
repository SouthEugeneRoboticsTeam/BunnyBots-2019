package org.sert2521.bunnybots2019

import org.sert2521.sertain.motors.TalonId
import org.sert2521.sertain.motors.VictorId
import edu.wpi.first.wpilibj.buttons.JoystickButton
import org.sert2521.bunnybots2019.tubintake.TubIntake

object MotorControllers {
    val leftFront = TalonId(3)
    val leftBack = TalonId(4)
    val rightFront = TalonId(2)
    val rightBack = TalonId(1)

    val TUBINTAKE_WHEEL_LEFT = TalonId(8)
    val TUBINTAKE_WHEEL_RIGHT = TalonId(9)

    val TUBINTAKE_ARM_A = TalonId(7)
    val TUBINTAKE_ARM_B = TalonId(6)

    val BEDDUMPER = TalonId(11)
}

object Sensors {
    val TUBINTAKE_LIMIT_TOP = 0
}

object Operator {
    val PRIMARY_CONTROLLER = 0
    val PRIMARY_STICK = 2
    val SECONDARY_STICK = 1

    val TUBINTAKE_IN_BUTTON = 8
    val TUBINTAKE_OUT_BUTTON = 9
    val TUBINTAKE_ARM_UP_BUTTON = 2
    val TUBINTAKE_ARM_DOWN_BUTTON = 1
}
