package io.github.mmolosay.musicmind.theory.tuning.system

import io.github.mmolosay.musicmind.theory.Constants.OctaveRatioDouble
import io.github.mmolosay.musicmind.theory.cents.Cents
import io.github.mmolosay.musicmind.theory.intervals.keys
import io.github.mmolosay.musicmind.theory.scales.FiniteIntervalScale
import io.github.mmolosay.musicmind.theory.scales.PureScales
import io.github.mmolosay.musicmind.theory.tuning.system.TuningSystem.Step
import kotlin.math.pow
import kotlin.math.roundToInt

/**
 * [Equal temperament – Wikipedia](https://en.wikipedia.org/wiki/Equal_temperament)
 */
class EqualTemperament(
    override val pitchClasses: Int,
) : TuningSystem {

    override val ionianScale: FiniteIntervalScale by lazy {
        var sum = 0
        PureScales.Ionian.map { pure ->
            ((pure.cents / step.cents).roundToInt() - sum)
                .also { sum += it }
                .keys
        }.let { FiniteIntervalScale(it) }
    }

    override val step by lazy {
        Step.Constant(Cents.Octave / pitchClasses)
    }
}

val EqualTemperament.oneStepRatio: Double
    get() = OctaveRatioDouble.pow(1.0 / pitchClasses) // pitchClasses-th root of 2 (octave ratio): n√2