package org.sert2521.bunnybots2019.drivetrain

import edu.wpi.first.wpilibj.Preferences

val driveSpeedScalar get() = Preferences.getInstance().getDouble("drive_speed_scalar", 1.0)
