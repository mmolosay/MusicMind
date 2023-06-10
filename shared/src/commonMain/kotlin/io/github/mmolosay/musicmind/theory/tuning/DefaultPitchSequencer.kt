package io.github.mmolosay.musicmind.theory.tuning

import io.github.mmolosay.musicmind.theory.Constants.OctaveRatioDouble
import io.github.mmolosay.musicmind.theory.pitch.Pitch
import io.github.mmolosay.musicmind.theory.pitch.hz
import io.github.mmolosay.musicmind.theory.tuning.instrument.FretboardTuning
import io.github.mmolosay.musicmind.theory.tuning.instrument.KeyboardTuning
import io.github.mmolosay.musicmind.theory.tuning.system.EqualTemperament
import io.github.mmolosay.musicmind.theory.tuning.system.PureIntonation
import io.github.mmolosay.musicmind.theory.tuning.system.TuningSystem
import io.github.mmolosay.musicmind.theory.tuning.system.oneStepRatio
import java.math.MathContext
import java.math.RoundingMode
import kotlin.math.absoluteValue
import kotlin.math.pow

// TODO: abolish interface and use just PitchSequencer as a class?
class DefaultPitchSequencer : PitchSequencer {

    override fun TuningSystem.plus(tuning: KeyboardTuning): Sequence<Pitch> =
        when (this) {
            is EqualTemperament -> this + tuning
            is PureIntonation -> this + tuning
        }

    override fun TuningSystem.plus(tuning: FretboardTuning): List<Sequence<Pitch>> =
        when (this) {
            is EqualTemperament -> this + tuning
            is PureIntonation -> this + tuning
        }

    private operator fun EqualTemperament.plus(tuning: KeyboardTuning): Sequence<Pitch> =
        sequence {
            val c = MathContext(15, RoundingMode.HALF_UP)
            val ratio = oneStepRatio.toBigDecimal(c)
            val a4Frequency = tuning.a4Frequency.toBigDecimal(c)
            val one = 1.0.toBigDecimal(c)
            var ordinal = 1
            /*
             * Calculates frequency of key with given ordinal.
             * Works only for keyboards of standard size = 88 or smaller.
             * If you have a keyboard with keys prior to A0 (first key on standard size keyboard),
             * you must use steps distance between reference A4 and desired key for calculations.
             */
            while (true) {
                val stepsDistance = ordinal - KeyboardTuning.A4Ordinal
                val b = with (PitchCalculator) { tuning.a4Frequency.hz.plusSteps(stepsDistance, this@plus) }
                println(b)
//                yield(b)
                val rawOffset = ratio.pow(stepsDistance.absoluteValue, c)
                println(rawOffset)
                // Double.pow() does this automatically with negative powers: n < 0, x.pow(n) = 1 / x.pow(-n)
                val a4Offset = rawOffset.takeUnless { stepsDistance < 0 } ?: one.divide(rawOffset, c)
                val frequency = (a4Offset * a4Frequency)
                    .setScale(5, RoundingMode.HALF_UP)
                    .toFloat()
                yield(frequency.hz)
                ordinal++
            }
        }

    private operator fun EqualTemperament.plus(tuning: FretboardTuning): List<Sequence<Pitch>> {
        val strings = tuning.openStrings.size
        return List(strings) { i ->
            sequence {
                val openString = tuning.openStrings[i]
                val openStringFrequency = openString.frequency.toBigDecimal()
                var ordinal = 1
                val pitchClasses = pitchClasses.toDouble()
                while (true) {
                    val multiplier = OctaveRatioDouble.pow((ordinal - 1) / pitchClasses).toBigDecimal()
                    val hz = (openStringFrequency * multiplier).toFloat()
                    yield(Pitch(hz))
                    ordinal++
                }
            }
        }
    }

    private operator fun PureIntonation.plus(tuning: KeyboardTuning): Sequence<Pitch> =
        TODO()

    private operator fun PureIntonation.plus(tuning: FretboardTuning): List<Sequence<Pitch>> =
        TODO()
}