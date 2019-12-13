package org.sert2521.bunnybots2019.oi

import edu.wpi.first.wpilibj.Joystick
import kotlinx.coroutines.CoroutineScope
import org.sert2521.bunnybots2019.PRIMARY_STICK
import org.sert2521.bunnybots2019.rollerintake.roll
import org.sert2521.sertain.coroutines.watch

val primaryJoystick by lazy { Joystick(PRIMARY_STICK) }
fun CoroutineScope.joystickInputs() {
    { primaryJoystick.getRawButton(1) }.watch {
        whileTrue {
            roll()
        }
    }
}
