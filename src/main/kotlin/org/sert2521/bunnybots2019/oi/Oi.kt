package org.sert2521.bunnybots2019.oi

import edu.wpi.first.wpilibj.Joystick
import kotlinx.coroutines.CoroutineScope
import org.sert2521.bunnybots2019.PRIMARY_STICK

val primaryJoystick by lazy { Joystick(PRIMARY_STICK) }

fun CoroutineScope.getInputs() {

}