package org.sert2521.bunnybots2019.oi

import edu.wpi.first.wpilibj.Joystick
import org.sert2521.bunnybots2019.PRIMARY_STICK
import org.sert2521.sertain.networktables.Table

val primaryJoystick by lazy { Joystick(PRIMARY_STICK) }

object WatashiNoTable : Table("WatashiNoTable") {
    var x by entry(5)
}
