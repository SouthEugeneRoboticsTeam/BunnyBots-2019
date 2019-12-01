package org.sert2521.bunnybots2019

import org.sert2521.sertain.motors.TalonId
import org.sert2521.sertain.motors.VictorId

object MotorControllers {
    val TUBINTAKE_WHEEL_LEFT = TalonId(99999999)
    val TUBINTAKE_WHEEL_RIGHT = TalonId(99999999)

    val TUBINTAKE_ARM_A = TalonId(999999)
    val TUBINTAKE_ARM_B = TalonId(999999)
}

object Sensors {
    val TUBINTAKE_LIMIT_TOP = 9999
    val TUBINTAKE_LIMIT_BOTTOM = 9999
}