package io.github.mmolosay.musicmind.theory.instruments.discrete

import io.github.mmolosay.musicmind.theory.distance.Distance
import io.github.mmolosay.musicmind.theory.instruments.DiscretePitchInstrument.Key
import io.github.mmolosay.musicmind.theory.instruments.DiscretePitchInstrument.Note
import io.github.mmolosay.musicmind.theory.tuning.PitchProducer
import io.github.mmolosay.musicmind.theory.tuning.instrument.InstrumentTuning
import io.github.mmolosay.musicmind.theory.tuning.system.TuningSystem

data class DiscretePitchInstrumentImpl internal constructor(
    override val keys: Int,
    override val tuningSystem: TuningSystem,
    override val tuning: InstrumentTuning,
    private val pitchProducer: PitchProducer,
) : AbstractDiscretePitchInstrument() {

    override val notes by lazy {
        with(pitchProducer) { pitches() }
            .mapIndexed { i, pitch -> Note(key = Key(ordinal = i + 1), pitch = pitch) }
    }

    override fun Note.scale(intervals: List<Distance>): List<Note>? {
        if (!exists) return null
        val notes = mutableListOf(this)
        var lastNote = this
        intervals.forEach {
            val note = lastNote + it
            if (note != null) {
                notes.add(note)
                lastNote = note
            } else {
                return notes
            }
        }
        return notes
    }

}