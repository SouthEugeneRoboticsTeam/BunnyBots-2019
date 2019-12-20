package org.sert2521.bunnybots2019.oi

import edu.wpi.first.wpilibj.Joystick
import org.sert2521.bunnybots2019.Operator

object Controls {
    val primaryJoystick by lazy { Joystick(Operator.PRIMARY_STICK) }
    val tubintakeInButton get() = primaryJoystick.getRawButton(Operator.TUBINTAKE_IN_BUTTON)
    val tubintakeOutButton get() = primaryJoystick.getRawButton(Operator.TUBINTAKE_OUT_BUTTON)
    val tubintakeArmUpButton get() = primaryJoystick.getRawButton(Operator.TUBINTAKE_ARM_UP_BUTTON)
    val tubintakeArmDownButton get() = primaryJoystick.getRawButton(Operator.TUBINTAKE_ARM_DOWN_BUTTON)
}
