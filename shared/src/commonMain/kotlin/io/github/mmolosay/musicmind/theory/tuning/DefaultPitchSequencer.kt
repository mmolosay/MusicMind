package io.github.mmolosay.musicmind.theory.tuning

import io.github.mmolosay.musicmind.theory.Constants.OctaveRatioDouble
import io.github.mmolosay.musicmind.theory.pitch.Pitch
import io.github.mmolosay.musicmind.theory.pitch.hz
import io.github.mmolosay.musicmind.theory.tuning.instrument.FretboardTuning
import io.github.mmolosay.musicmind.theory.tuning.instrument.KeyboardTuning
import io.github.mmolosay.musicmind.theory.tuning.system.EqualTemperament
import io.github.mmolosay.musicmind.theory.tuning.system.PureIntonation
import io.github.mmolosay.musicmind.theory.tuning.system.TuningSystem
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
            var ordinal = 1
            val a4Pitch = tuning.a4Frequency.hz
            while (true) {
                val stepsDistance = ordinal - KeyboardTuning.A4Ordinal
                val pitch = with(PitchCalculator) { a4Pitch.plusSteps(stepsDistance, this@plus) }
                yield(pitch)
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