package org.sert2521.bunnybots2019

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.sert2521.bunnybots2019.beddumper.BedDumper
import org.sert2521.bunnybots2019.tubintake.*
import org.sert2521.bunnybots2019.drivetrain.Drivetrain
import org.sert2521.bunnybots2019.oi.initControls
import org.sert2521.sertain.events.whileTeleop
import org.sert2521.bunnybots2019.cubeintake.CubeIntake
import org.sert2521.bunnybots2019.drivetrain.driveCurve
import org.sert2521.bunnybots2019.oi.controlModeChooser
import org.sert2521.sertain.events.onAuto
import org.sert2521.sertain.robot
import org.sert2521.sertain.subsystems.add
import org.sert2521.sertain.units.m
import org.sert2521.sertain.units.mps

suspend fun main() = robot {
    add<TubIntake>()
    add<BedDumper>()
    add<Drivetrain>()
    add<CubeIntake>()

    launch {
        delay(1000)
        SmartDashboard.putData("Control Mode", controlModeChooser);
    }

    onAuto {
        driveCurve(2.mps, 1.m)
    }

    whileTeleop {
        initControls()
    }
}
