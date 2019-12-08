package org.sert2521.bunnybots2019.tubintake

import org.sert2521.bunnybots2019.MotorControllers
import org.sert2521.bunnybots2019.Sensors
import org.sert2521.bunnybots2019.utils.timer
import org.sert2521.sertain.coroutines.RobotScope
import org.sert2521.sertain.input.digitalInput
import org.sert2521.sertain.motors.Encoder
import org.sert2521.sertain.motors.MotorController
import org.sert2521.sertain.subsystems.Subsystem

object TubIntake : Subsystem("TubIntake") {

    private val armDrive = MotorController(
            MotorControllers.TUBINTAKE_ARM_A,
            MotorControllers.TUBINTAKE_ARM_B
    ) {
        inverted = false
        brakeMode = true
        encoder = Encoder(ENCODER_TICKS)

        maxOutputRange = -0.75..0.75

        openLoopRamp = 0.25

        pidf {
            kp = ARM_KP
            ki = ARM_KI
            kd = ARM_KD
        }
    }

    val position get() = armDrive.position

    private val topLimitSwitch = digitalInput(Sensors.TUBINTAKE_LIMIT_TOP) {
        RobotScope.whenFalse {
            armDrive.position = 0
            println("tub intake arm at top limit")
        }
    }
    private val bottomLimitSwitch = digitalInput(Sensors.TUBINTAKE_LIMIT_BOTTOM) {
        RobotScope.whenFalse {
            armDrive.position = ARM_DOWN_TICKS
        }
    }

    val atTop get() = topLimitSwitch.get()
    val atBottom get() = bottomLimitSwitch.get()

    suspend fun runArmToPosition(endPosition: Double) {
        val timerPeriod: Long = 20
        var elapsedTime: Long = 0
        //val motionCurve = generateMotionCurve(armDrive.position, endPosition, curveDuration)
        var targetPosition: Double = 0.0 //motionCurve.getTarget(0)

        timer(timerPeriod, 5000) {
            elapsedTime += timerPeriod
            //targetPosition = motionCurve.getTarget(elapsedTime);
            armDrive.setPosition(targetPosition)
        }
    }

    private val wheelDrive = MotorController(
            MotorControllers.TUBINTAKE_WHEEL_LEFT,
            MotorControllers.TUBINTAKE_WHEEL_RIGHT
    ) {
        inverted = false
        eachFollower {
            inverted = true
        }
    }

    var intakeRunning = false
        private set

    fun spinIntake() {
        wheelDrive.setPercentOutput(INTAKE_SPEED)
        intakeRunning = true
    }

    fun spinOutake() {
        wheelDrive.setPercentOutput(-OUTTAKE_SPEED)
        intakeRunning = true
    }

    fun stopSpin() {
        wheelDrive.disable()
        intakeRunning = false
    }
}