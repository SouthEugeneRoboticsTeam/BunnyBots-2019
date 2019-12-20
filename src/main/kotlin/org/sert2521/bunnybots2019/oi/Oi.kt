package org.sert2521.bunnybots2019.oi

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.XboxController
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import kotlinx.coroutines.CoroutineScope
import org.sert2521.bunnybots2019.Operator
import org.sert2521.bunnybots2019.cubeintake.intakeCubes
import org.sert2521.bunnybots2019.cubeintake.outtakeCubes
import org.sert2521.sertain.coroutines.watch
import org.sert2521.bunnybots2019.tubintake.tubIntake
import org.sert2521.bunnybots2019.tubintake.tubOuttake

enum class ControlMode {
    CONTROLLER, JOYSTICK
}

val controlModeChooser = SendableChooser<ControlMode>().apply {
    addOption("Joystick", ControlMode.JOYSTICK)
    addOption("Controller", ControlMode.CONTROLLER)
}

val controlMode get() = controlModeChooser.selected ?: ControlMode.CONTROLLER

val primaryJoystick by lazy { Joystick(Operator.PRIMARY_STICK) }
val secondaryJoystick by lazy { Joystick(Operator.SECONDARY_STICK) }
val primaryController by lazy { XboxController(Operator.PRIMARY_CONTROLLER) }

fun CoroutineScope.getInputs() {
    SmartDashboard.putData("Control Mode", controlModeChooser);

    { primaryJoystick.getRawButton(Operator.CUBEINTAKE_BUTTON) }.watch {
        whileTrue {
            intakeCubes()
        }
    };
    { secondaryJoystick.getRawButton(Operator.CUBEOUTTAKE_BUTTON) }.watch {
        whileTrue {
            outtakeCubes()
        }
    };
    { secondaryJoystick.getRawButton(Operator.TUBINTAKE_IN_BUTTON) }.watch {
        whileTrue {
            println("Intake should be spinning in")
            tubIntake()
        }
    };
    { secondaryJoystick.getRawButton(Operator.TUBINTAKE_OUT_BUTTON) }.watch {
        whileTrue {
            println("Outtake should be running")
            tubOuttake()
        }
    };
    { primaryController.backButton }.watch {
        whileTrue {
            intakeCubes()
        }
    }
}
