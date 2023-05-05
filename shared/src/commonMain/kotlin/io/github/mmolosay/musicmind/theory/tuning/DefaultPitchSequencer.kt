package io.github.mmolosay.musicmind.theory.tuning

import io.github.mmolosay.musicmind.theory.pitch.Pitch
import io.github.mmolosay.musicmind.theory.pitch.Pitches
import io.github.mmolosay.musicmind.theory.tuning.instrument.InstrumentTuning
import io.github.mmolosay.musicmind.theory.tuning.instrument.PianoTuning
import io.github.mmolosay.musicmind.theory.tuning.system.EqualTemperament
import io.github.mmolosay.musicmind.theory.tuning.system.PureIntonation
import io.github.mmolosay.musicmind.theory.tuning.system.TuningSystem
import java.text.DecimalFormat
import kotlin.math.pow

class DefaultPitchSequencer : PitchSequencer {

    /** A format that removes decimal digits after first 6 */
    private val format = DecimalFormat("#.######")

    override fun TuningSystem.plus(tuning: InstrumentTuning): Sequence<Pitch> =
        when (this) {
            is EqualTemperament -> this + tuning
            is PureIntonation -> this + tuning
        }

    private operator fun EqualTemperament.plus(tuning: InstrumentTuning): Sequence<Pitch> =
        when (tuning) {
            is PianoTuning -> this + tuning
        }

    private operator fun EqualTemperament.plus(tuning: PianoTuning): Sequence<Pitch> =
        sequence {
            val oneStepFrequencyRatio = Pitches.OctaveRatio.pow(1.0 / pitchClasses)
            var ordinal = 1
            while (true) {
                val power = ordinal - PianoTuning.A4Ordinal
                val a4Offset = oneStepFrequencyRatio.pow(power).toBigDecimal()
                val hz = format.format(a4Offset * tuning.a4Frequency).toDouble()
                yield(Pitch(hz))
                ordinal++
            }
        }

    private operator fun PureIntonation.plus(tuning: InstrumentTuning): Sequence<Pitch> =
        TODO()
}