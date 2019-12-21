package org.sert2521.bunnybots2019

import org.sert2521.bunnybots2019.beddumper.BedDumper
import org.sert2521.bunnybots2019.tubintake.*
import org.sert2521.bunnybots2019.drivetrain.Drivetrain
import org.sert2521.bunnybots2019.oi.initControls
import org.sert2521.sertain.events.whileTeleop
import org.sert2521.bunnybots2019.cubeintake.CubeIntake
import org.sert2521.sertain.events.onTeleop
import org.sert2521.sertain.motors.MotorController
import org.sert2521.sertain.motors.VictorId
import org.sert2521.sertain.robot
import org.sert2521.sertain.subsystems.add

suspend fun main() = robot {
    add<TubIntake>()
    add<BedDumper>()
    add<Drivetrain>()
    add<CubeIntake>()

    whileTeleop {
        initControls()
        teleopIntakeControl()
    }
}
