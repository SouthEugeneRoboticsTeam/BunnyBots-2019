package org.sert2521.bunnybots2019.oi

import edu.wpi.first.wpilibj.Joystick
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.sert2521.bunnybots2019.Operator
import org.sert2521.bunnybots2019.intake.Intake
import org.sert2521.bunnybots2019.intake.intake
import org.sert2521.sertain.coroutines.watch
import org.sert2521.sertain.subsystems.access

val primaryJoystick by lazy { Joystick(Operator.PRIMARY_STICK) }

fun CoroutineScope.getInputs() {
    ({ primaryJoystick.getRawButton(3) }).watch {
        whileTrue {
            intake()
        }
    }
}
