package org.sert2521.bunnybots2019

import org.sert2521.sertain.motors.TalonId

val rightFront = TalonId(3)
val rightBack = TalonId(4)
val leftFront = TalonId(1)
val leftBack = TalonId(2)

object Operator {
    const val PRIMARY_CONTROLLER = 0
    const val PRIMARY_STICK = 2
    const val SECONDARY_STICK = 1
}
