package org.sert2521.bunnybots2019

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.can.TalonSRX
import com.ctre.phoenix.motorcontrol.can.VictorSPX
import org.sert2521.bunnybots2019.tubintake.*
import org.sert2521.bunnybots2019.drivetrain.Drivetrain
import org.sert2521.bunnybots2019.oi.getInputs
import org.sert2521.sertain.coroutines.periodic
import org.sert2521.sertain.events.onTeleop
import org.sert2521.sertain.events.whileTeleop
import org.sert2521.sertain.robot
import org.sert2521.sertain.subsystems.access
import org.sert2521.sertain.subsystems.add

suspend fun main() = robot {
    add<TubIntake>()
    add<Drivetrain>()

    onTeleop {
        getInputs()
        teleopIntakeControl()
    }
}
