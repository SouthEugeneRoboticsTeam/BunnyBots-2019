package org.sert2521.bunnybots2019.drivetrain

import edu.wpi.first.wpilibj.Preferences
import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import kotlinx.coroutines.cancelAndJoin
import org.sert2521.bunnybots2019.Operator
import org.sert2521.bunnybots2019.drivetrain.Drivetrain
import org.sert2521.sertain.Robot
import org.sert2521.sertain.coroutines.delayUntil
import org.sert2521.sertain.events.onTick
import org.sert2521.sertain.subsystems.doTask

val driveSpeedScalar get() = Preferences.getInstance().getDouble("drive_speed_scalar", 1.0)

typealias DoubleRange = ClosedFloatingPointRange<Double>

fun DoubleRange.intersects(other: DoubleRange): Boolean =
        start in other || endInclusive in other

fun Number.remap(fromRange: DoubleRange, toRange: DoubleRange) =
        (this.toDouble() - fromRange.start) * (toRange.endInclusive - toRange.start) / (fromRange.endInclusive - fromRange.start) + toRange.start

val primaryJoystick by lazy { Joystick(Operator.PRIMARY_STICK) }

private val throttle get() = primaryJoystick.y
private val turn get() = primaryJoystick.x
private val scale get() = 1.0

suspend fun Robot.driveTrain() = doTask {
    val drivetrain = use<Drivetrain>()
    action {
        val job = onTick {

            //val throttleScalar = scale.remap(0.0..1.0, 0.5..1.0)
            //val turnScalar = scale.remap(0.0..1.0, 0.4..1.0)

            val scaledThrottle = throttle// * throttleScalar * -driveSpeedScalar
            val scaledTurn = turn// * turnScalar * driveSpeedScalar

            //drivetrain.arcadeDrive(scaledThrottle, scaledTurn)
        }
    }
}
