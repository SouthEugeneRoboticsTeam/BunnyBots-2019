package org.sert2521.bunnybots2019.beddumper;

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.NeutralMode
import com.ctre.phoenix.motorcontrol.can.VictorSPX
import edu.wpi.first.wpilibj.Servo
import kotlinx.coroutines.delay
import org.sert2521.bunnybots2019.MotorControllers
import org.sert2521.sertain.subsystems.Subsystem

class BedDumper : Subsystem("BedDumper") {
        private val conveyorMotor = VictorSPX(
                MotorControllers.BEDDUMPER.number
        ).apply {
                // inverted = true
                setNeutralMode(NeutralMode.Brake)
        }

        val servo1 = Servo(0)
        val servo2 = Servo(1)

        suspend fun setServoAngle(angle: Double) {
                servo1.angle = angle
                servo2.angle = angle
                delay(500)
        }

        private var conveyorRunning = false

        fun spinBedDumper() {
                conveyorMotor.set(ControlMode.PercentOutput, BED_DUMPER_SPEED)
                conveyorRunning = true
        }

        fun stopSpin() {
                conveyorMotor.neutralOutput()
                conveyorRunning = false
        }
}
