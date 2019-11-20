package org.sert2521.bunnybots2019.drivetrain

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import kotlinx.coroutines.cancelAndJoin
import org.sert2521.bunnybots2019.Operator
import org.sert2521.bunnybots2019.drivetrain.Drivetrain
import org.sert2521.sertain.Robot
import org.sert2521.sertain.coroutines.delayUntil
import org.sert2521.sertain.events.onTick
import org.sert2521.sertain.subsystems.doTask

//fun Number.remap(fromRange: DoubleRange, toRange: DoubleRange) =
//        (this.toDouble() - fromRange.start) * (toRange.endInclusive - toRange.start) / (fromRange.endInclusive - fromRange.start) + toRange.start

val primaryJoystick by lazy { Joystick(Operator.PRIMARY_STICK) }

private val throttle get() = primaryJoystick.y
private val turn get() = primaryJoystick.x
private val scale get() = 1.0

suspend fun Robot.driveTrain(speed: Double) = doTask {
    val bedDumper = use<Drivetrain>()
    action {
        val job = onTick {

//            val throttleScalar = scale.remap(0.0..1.0, 0.5..1.0)
//            val turnScalar = scale.remap(0.0..1.0, 0.4..1.0)

//            val scaledThrottle = throttle * throttleScalar * liftScalar * -driveSpeedScalar
//            val scaledTurn = turn * turnScalar * liftScalar * driveSpeedScalar
        }
    }
}
