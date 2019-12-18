package org.sert2521.bunnybots2019.oi

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import kotlinx.coroutines.CoroutineScope
import org.sert2521.bunnybots2019.Operator

enum class ControlMode {
    CONTROLLER, JOYSTICK
}

val controlModeChooser = SendableChooser<ControlMode>().apply {
    addOption("Joystick", ControlMode.JOYSTICK)
    addOption("Controller", ControlMode.CONTROLLER)
}

val controlMode get() = controlModeChooser.selected ?: ControlMode.CONTROLLER

val primaryJoystick by lazy { Joystick(Operator.PRIMARY_STICK) }

fun CoroutineScope.getInputs() {
    SmartDashboard.putData("Control Mode", controlModeChooser)
}