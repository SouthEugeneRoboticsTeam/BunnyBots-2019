package org.sert2521.bunnybots2019.tubintake

import edu.wpi.first.wpilibj.DigitalInput
import edu.wpi.first.wpilibj.InterruptHandlerFunction
import org.sert2521.bunnybots2019.MotorControllers
import org.sert2521.bunnybots2019.Sensors
import org.sert2521.bunnybots2019.utils.timer
import org.sert2521.sertain.motors.Encoder
import org.sert2521.sertain.motors.MotorController
import org.sert2521.sertain.subsystems.Subsystem
import kotlin.math.abs
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class TubIntake : Subsystem("TubIntake") {
    private val wheelDrive = MotorController(
            MotorControllers.TUBINTAKE_WHEEL_LEFT,
            MotorControllers.TUBINTAKE_WHEEL_RIGHT
    ) {
        eachFollower {
            inverted = true
        }
    }

    var intakeRunning = false
        private set

    private val armDrive = MotorController(
            MotorControllers.TUBINTAKE_ARM_A,
            MotorControllers.TUBINTAKE_ARM_B
    ) {
        ctreMotorController.configFactoryDefault()
        encoder = Encoder(ENCODER_TICKS)
        position = 0
        inverted = true
        brakeMode = true

        pidf {
            kp = ARM_KP
            ki = ARM_KI
            kd = ARM_KD
            maxOutput = 1.0
        }
    }

    var position
        get() = armDrive.position
        set(value) {
            armDrive.position = value
        }

    var armRunning = false
        private set

    private val topLimitSwitch = DigitalInput(Sensors.TUBINTAKE_LIMIT_TOP)

    init {
        topLimitSwitch.requestInterrupts(object : InterruptHandlerFunction<Boolean>() {
            override fun interruptFired(interruptAssertedMask: Int, param: Boolean?) {
                    armDrive.position = 0
            }
        })
       topLimitSwitch.setUpSourceEdge(false, true)
       topLimitSwitch.enableInterrupts()
    }

    val atTop get() = !topLimitSwitch.get()

    suspend fun runArmToPosition(endPosition: Int) {
        armRunning = true

        val initialPosition = armDrive.position
        val positionDifference = endPosition.coerceAtMost(ARM_DOWN_TICKS).coerceAtLeast(ARM_UP_TICKS) - initialPosition
        val curveDurationModifier: Long = if (endPosition == ARM_DOWN_TICKS) 1000 else 1000
        val curveDuration: Long = curveDurationModifier + 1000 * abs(positionDifference.toDouble() / (ARM_UP_TICKS - ARM_DOWN_TICKS).toDouble())
                .roundToLong()
        val timerPeriod: Long = 20
        var elapsedTime: Long = 0

        timer(timerPeriod, curveDuration) {
            elapsedTime += timerPeriod
            val percentDone: Int = (100.0 * (elapsedTime.toDouble() / curveDuration.toDouble()))
                    .roundToInt()
                    .coerceAtLeast(0)
                    .coerceAtMost(100)

            val targetPosition: Int = ((CURVE_COEFFICIENTS[percentDone] * positionDifference.toDouble()) + initialPosition)
                    .roundToInt()

            armDrive.setTargetPosition(targetPosition)
        }
        armRunning = false
    }

    fun spinIntake() {
        wheelDrive.setPercentOutput(INTAKE_SPEED)
        intakeRunning = true
    }

    fun spinOuttake() {
        wheelDrive.setPercentOutput(-OUTTAKE_SPEED)
        intakeRunning = true
    }

    fun stopSpin() {
        wheelDrive.disable()
        intakeRunning = false
    }
}