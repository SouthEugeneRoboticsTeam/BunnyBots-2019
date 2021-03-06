package org.sert2521.bunnybots2019.oi

import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.XboxController
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import kotlinx.coroutines.CoroutineScope
import org.sert2521.bunnybots2019.Operator
import org.sert2521.bunnybots2019.beddumper.dumpBed
import org.sert2521.sertain.coroutines.watch
import org.sert2521.bunnybots2019.cubeintake.intakeCubes
import org.sert2521.bunnybots2019.cubeintake.outtakeCubes
import org.sert2521.bunnybots2019.tubintake.ARM_DOWN_TICKS
import org.sert2521.bunnybots2019.tubintake.ARM_UP_TICKS
import org.sert2521.bunnybots2019.tubintake.intakeTub
import org.sert2521.bunnybots2019.tubintake.outtakeTub
import org.sert2521.bunnybots2019.tubintake.runArmTo

enum class ControlMode {
    CONTROLLER, JOYSTICK
}

val controlModeChooser = SendableChooser<ControlMode>().apply {
    addOption("Joystick", ControlMode.JOYSTICK)
    addOption("Controller", ControlMode.CONTROLLER)
}

val controlMode get() = controlModeChooser.selected ?: ControlMode.JOYSTICK

val primaryJoystick by lazy { Joystick(Operator.PRIMARY_STICK) }
val secondaryJoystick by lazy { Joystick(Operator.SECONDARY_STICK) }
val primaryController by lazy { XboxController(Operator.PRIMARY_CONTROLLER) }

fun CoroutineScope.initControls() {
//    SmartDashboard.putData("Control Mode", controlModeChooser);

    { secondaryJoystick.getRawButton(Operator.CUBEOUTTAKE_BUTTON) }.watch {
        whileTrue {
            println("Cube outtake should be running")
            outtakeCubes()
        }
    };
    { secondaryJoystick.getRawButton(Operator.TUBINTAKE_IN_BUTTON) }.watch {
        whileTrue {
            println("Tub intake should be spinning")
            intakeTub()
        }
    };
    { secondaryJoystick.getRawButton(Operator.TUBINTAKE_OUT_BUTTON) }.watch {
        whileTrue {
            println("Tub outtake should be running")
            outtakeTub()
        }
    };
    { secondaryJoystick.getRawButton(Operator.BEDDUMP_BUTTON) }.watch {
        whileTrue {
            println("Dumping bed")
            dumpBed()
        }
    };
    { secondaryJoystick.getRawButton(Operator.TUBINTAKE_ARM_UP_BUTTON) }.watch {
        whenTrue {
            runArmTo(ARM_UP_TICKS)
        }
    };
    { secondaryJoystick.getRawButton(Operator.TUBINTAKE_ARM_DOWN_BUTTON) }.watch {
        whenTrue {
            runArmTo(ARM_DOWN_TICKS)
        }
    }

    if (controlMode == ControlMode.JOYSTICK) {
        { primaryJoystick.getRawButton(Operator.CUBEINTAKE_BUTTON) }.watch {
            whileTrue {
                println("Cube intake should be running")
                intakeCubes()
            }
        };
    } else if (controlMode == ControlMode.CONTROLLER) {
        { primaryController.getBumper(GenericHID.Hand.kRight) }.watch {
            whileTrue {
                println("Intaking cubes using the controller!")
                intakeCubes()
            }
        };
    }
}
