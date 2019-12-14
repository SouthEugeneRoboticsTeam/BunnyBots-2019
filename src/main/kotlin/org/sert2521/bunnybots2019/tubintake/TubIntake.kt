package org.sert2521.bunnybots2019.tubintake

import org.sert2521.bunnybots2019.MotorControllers
import org.sert2521.bunnybots2019.Sensors
import org.sert2521.bunnybots2019.utils.timer
import org.sert2521.sertain.coroutines.RobotScope
import org.sert2521.sertain.input.digitalInput
import org.sert2521.sertain.motors.Encoder
import org.sert2521.sertain.motors.MotorController
import org.sert2521.sertain.subsystems.Subsystem
import kotlin.math.roundToInt

class TubIntake : Subsystem("TubIntake") {
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

    val position get() = armDrive.sensorPosition

    var armRunning = false
        private set

//    private val topLimitSwitch = digitalInput(Sensors.TUBINTAKE_LIMIT_TOP) {
//        RobotScope.whenFalse {
//            armDrive.sensorPosition = ARM_UP_TICKS
//            println("tub intake arm at top limit")
//        }
//    }
    //maybe dont uncomment this
//    private val bottomLimitSwitch = digitalInput(Sensors.TUBINTAKE_LIMIT_BOTTOM) {
//        RobotScope.whenFalse {
//            armDrive.sensorPosition = ARM_DOWN_TICKS
//            println("tub intake arm at bottom limit")
//        }
//    }
//
//    val atTop get() = !topLimitSwitch.get()
//    val atBottom get() = !bottomLimitSwitch.get()

    private val curveCoefficients = arrayOf(0.0, 0.0073915413442819725, 0.008162571153159898,
            0.009013298652847826, 0.009951801866904328, 0.010986942630593181, 0.012128434984274239,
            0.013386917827664782, 0.014774031693273059, 0.01630249937144095, 0.017986209962091562,
            0.019840305734077513, 0.02188127093613048, 0.0241270214176692, 0.02659699357686586,
            0.029312230751356326, 0.03229546469845052, 0.03557118927263618, 0.039165722796764356,
            0.04310725494108612, 0.04742587317756679, 0.052153563078417745, 0.05732417589886876,
            0.06297335605699649, 0.06913842034334682, 0.07585818002124356, 0.08317269649392238,
            0.09112296101485616, 0.09975048911968513, 0.10909682119561294, 0.11920292202211757,
            0.13010847436299786, 0.1418510649004878, 0.15446526508353473, 0.16798161486607552,
            0.18242552380635635, 0.19781611144141828, 0.2141650169574414, 0.23147521650098238,
            0.24973989440488234, 0.2689414213699951, 0.28905049737499605, 0.31002551887238755,
            0.33181222783183395, 0.35434369377420455, 0.3775406687981454, 0.401312339887548,
            0.4255574831883411, 0.45016600268752216, 0.47502081252106, 0.5, 0.52497918747894,
            0.549833997312478, 0.574442516811659, 0.598687660112452, 0.6224593312018546, 0.6456563062257954,
            0.668187772168166, 0.6899744811276125, 0.7109495026250039, 0.7310585786300049, 0.7502601055951177,
            0.7685247834990175, 0.7858349830425586, 0.8021838885585817, 0.8175744761936437, 0.8320183851339245,
            0.8455347349164652, 0.8581489350995123, 0.8698915256370021, 0.8807970779778823, 0.8909031788043871,
            0.9002495108803148, 0.9088770389851438, 0.9168273035060777, 0.9241418199787566, 0.9308615796566531,
            0.9370266439430035, 0.9426758241011313, 0.9478464369215823, 0.9525741268224331, 0.9568927450589139,
            0.9608342772032357, 0.9644288107273639, 0.9677045353015494, 0.9706877692486436, 0.973403006423134,
            0.9758729785823308, 0.9781187290638694, 0.9801596942659225, 0.9820137900379085, 0.9836975006285591,
            0.9852259683067269, 0.9866130821723351, 0.9878715650157257, 0.9890130573694068, 0.9900481981330957,
            0.990986701347152, 0.9918374288468401, 0.9926084586557181, 1.0)

    suspend fun runArmToPosition(endPosition: Int) {
        armRunning = true

        val initialPosition = armDrive.sensorPosition
        val positionDifference = endPosition.coerceAtMost(ARM_DOWN_TICKS).coerceAtLeast(ARM_UP_TICKS) - initialPosition

        val curveDuration: Long = 2500
        val timerPeriod: Long = 20
        var elapsedTime: Long = 0

        timer(timerPeriod, curveDuration) {
            elapsedTime += timerPeriod
            val percentDone = (100.0 * (elapsedTime.toDouble() / curveDuration.toDouble()))
                    .roundToInt()
                    .coerceAtLeast(0)
                    .coerceAtMost(100)

            val targetPosition = (curveCoefficients[percentDone] * positionDifference.toDouble()).roundToInt() + initialPosition
            armDrive.setPosition(targetPosition)
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