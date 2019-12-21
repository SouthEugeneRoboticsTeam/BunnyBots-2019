package org.sert2521.bunnybots2019.oi

import org.sert2521.bunnybots2019.Operator

object Controls {
    val tubintakeArmUpButton get() = secondaryJoystick.getRawButton(Operator.TUBINTAKE_ARM_UP_BUTTON)
    val tubintakeArmDownButton get() = secondaryJoystick.getRawButton(Operator.TUBINTAKE_ARM_DOWN_BUTTON)
}
