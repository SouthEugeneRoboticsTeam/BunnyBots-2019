package org.sert2521.bunnybots2019

import org.sert2521.sertain.motors.TalonId
import org.sert2521.sertain.motors.VictorId
import edu.wpi.first.wpilibj.buttons.JoystickButton
import org.sert2521.bunnybots2019.tubintake.TubIntake

object MotorControllers {
  // DriveTrain motor ids
    val leftFront = TalonId(1)
    val leftBack = TalonId(2)
    val rightFront = TalonId(3)
    val rightBack = TalonId(4)
  
    val TUBINTAKE_WHEEL_LEFT = TalonId(4)
    val TUBINTAKE_WHEEL_RIGHT = TalonId(3)

    val TUBINTAKE_ARM_A = TalonId(2)
    val TUBINTAKE_ARM_B = TalonId(1)
}

object Sensors {
    val TUBINTAKE_LIMIT_TOP = 8
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