package io.github.mmolosay.musicmind.theory.tuning

import io.github.mmolosay.musicmind.theory.Constants
import io.github.mmolosay.musicmind.theory.pitch.Pitch
import io.github.mmolosay.musicmind.theory.tuning.instrument.FretboardTuning
import io.github.mmolosay.musicmind.theory.tuning.instrument.KeyboardTuning
import io.github.mmolosay.musicmind.theory.tuning.system.EqualTemperament
import io.github.mmolosay.musicmind.theory.tuning.system.PureIntonation
import io.github.mmolosay.musicmind.theory.tuning.system.TuningSystem
import io.github.mmolosay.musicmind.theory.tuning.system.oneStepRatio
import java.text.DecimalFormat
import kotlin.math.pow

class DefaultPitchSequencer : PitchSequencer {

    /** A format that removes decimal digits after first 6 */
    private val format = DecimalFormat("#.######")

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
            val ratio = oneStepRatio
            var ordinal = 1
            while (true) {
                val power = ordinal - KeyboardTuning.A4Ordinal
                val a4Offset = ratio.pow(power).toBigDecimal()
                val hz = format.format(a4Offset * tuning.a4Frequency).toDouble()
                yield(Pitch(hz))
                ordinal++
            }
        }

    private operator fun EqualTemperament.plus(tuning: FretboardTuning): List<Sequence<Pitch>> {
        val strings = tuning.openStrings.size
        return List(strings) { i ->
            sequence {
                val openString = tuning.openStrings[i]
                yield(openString) // first is open string
                val openStringFrequency = openString.frequency.toBigDecimal()
                var ordinal = 1
                while (true) { // and then its frets
                    val multiplier = Constants.OctaveRatio.pow(ordinal / pitchClasses).toBigDecimal()
                    val hz = format.format(openStringFrequency * multiplier).toDouble()
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