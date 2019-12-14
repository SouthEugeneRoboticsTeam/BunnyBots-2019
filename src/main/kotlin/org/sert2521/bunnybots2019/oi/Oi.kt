package org.sert2521.bunnybots2019.oi

import edu.wpi.first.wpilibj.Joystick
import kotlinx.coroutines.CoroutineScope
import org.sert2521.bunnybots2019.Operator
import org.sert2521.bunnybots2019.rollerintake.roll
import org.sert2521.sertain.coroutines.watch

val primaryJoystick by lazy { Joystick(Operator.PRIMARY_STICK) }

fun CoroutineScope.getInputs() {
    { primaryJoystick.getRawButton(1) }.watch {
        whileTrue {
            roll()
        }
    }
}