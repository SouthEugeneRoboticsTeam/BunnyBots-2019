package org.sert2521.bunnybots2019.oi

import edu.wpi.first.wpilibj.Joystick
import kotlinx.coroutines.CoroutineScope
import org.sert2521.bunnybots2019.Operator
import org.sert2521.bunnybots2019.tubintake.tubIntake
import org.sert2521.bunnybots2019.tubintake.tubOuttake
import org.sert2521.sertain.coroutines.watch

val primaryJoystick by lazy { Joystick(Operator.PRIMARY_STICK) }

fun CoroutineScope.getInputs() {
    { primaryJoystick.getRawButton(Operator.TUBINTAKE_IN_BUTTON) }.watch() {
        whileTrue() {
            println("Intake should be spinning in")
            tubIntake()
        }
    };
    { primaryJoystick.getRawButton(Operator.TUBINTAKE_OUT_BUTTON) }.watch() {
        whileTrue() {
            println("Outtake should be running")
            tubOuttake()
        }
    }
}
