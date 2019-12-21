package org.sert2521.bunnybots2019.tubintake
import com.ctre.phoenix.motorcontrol.can.VictorSPX
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

class TubIntake : Subsystem("TubIntake", ::teleopArmControl) {
    val vicky = VictorSPX(9).apply {
        inverted = true
    }
    private val wheelDrive = MotorController(
            MotorControllers.TUBINTAKE_WHEEL_LEFT
    ) {
        vicky.follow(ctreMotorController)
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
        sensorInverted = true
        setTargetPosition(0)

        brakeMode = true

        pidf {
            kp = ARM_KP
            ki = ARM_KI
            kd = ARM_KD
            maxOutput = 1.0
        }
    }
    fun home() {
        armDrive.setPercentOutput(-0.1)
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
        val curveDurationModifier: Long = if (endPosition == ARM_DOWN_TICKS) 1000 else 1250
        val curveDuration: Long = curveDurationModifier + 650 * abs(positionDifference.toDouble() / (ARM_UP_TICKS - ARM_DOWN_TICKS).toDouble())
                .roundToLong()
        val timerPeriod: Long = 20
        var elapsedTime: Long = 0

        timer(timerPeriod, curveDuration) {
            println("Position: ${armDrive.position}")
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
        // wheelyDrive.set(ControlMode.PercentOutput, INTAKE_SPEED * 2)
        wheelDrive.setPercentOutput(1.0)
        println("Setting percent output to 1")
        intakeRunning = true
    }

    fun spinOuttake() {
        // wheelyDrive.set(ControlMode.PercentOutput, -INTAKE_SPEED * 2)
        wheelDrive.setPercentOutput(-1.0)
        println("Setting percent output to -1")
        intakeRunning = true
    }

    fun stopSpin() {
        wheelDrive.disable()
        intakeRunning = false
    }
}
