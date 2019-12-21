package org.sert2521.bunnybots2019.autonomous

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import org.sert2521.bunnybots2019.cubeintake.intakeCubes
import org.sert2521.bunnybots2019.drivetrain.driveCurve
import org.sert2521.bunnybots2019.tubintake.ARM_UP_TICKS
import org.sert2521.bunnybots2019.tubintake.autoRunArm
import org.sert2521.bunnybots2019.tubintake.intakeTub
import org.sert2521.sertain.coroutines.doOne
import org.sert2521.sertain.units.m
import org.sert2521.sertain.units.mps

suspend fun runAutonomous() {
    coroutineScope {
        doOne {
            action {
                driveCurve(5.mps, .3048.m)
            }
            action {
                intakeCubes()
            }
        }
    }
    intakeTub()
    autoRunArm(ARM_UP_TICKS)
}
