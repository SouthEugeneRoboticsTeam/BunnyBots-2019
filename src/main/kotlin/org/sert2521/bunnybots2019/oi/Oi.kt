package org.sert2521.bunnybots2019.oi

import edu.wpi.first.wpilibj.Joystick
import kotlinx.coroutines.CoroutineScope
import org.sert2521.bunnybots2019.Operator
import org.sert2521.bunnybots2019.beddumper.dumpBed
import org.sert2521.sertain.coroutines.watch

val primaryJoystick by lazy { Joystick(Operator.PRIMARY_STICK) }

fun CoroutineScope.initControls() {
    { primaryJoystick.getRawButton(14) }.watch {
        whileTrue {
            println("Dumping bed")
            dumpBed()
        }
    }
}
