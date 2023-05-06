package io.github.mmolosay.musicmind.theory.instruments.discrete

import io.github.mmolosay.musicmind.theory.instruments.DiscretePitchInstrument.Key
import io.github.mmolosay.musicmind.theory.instruments.DiscretePitchInstrument.Note
import io.github.mmolosay.musicmind.theory.partition.OctavePartition
import io.github.mmolosay.musicmind.theory.partition.verify
import io.github.mmolosay.musicmind.theory.pitch.PitchClassifier
import io.github.mmolosay.musicmind.theory.scales.FiniteNoteScale
import io.github.mmolosay.musicmind.theory.tuning.PitchProducer
import io.github.mmolosay.musicmind.theory.tuning.instrument.InstrumentTuning
import io.github.mmolosay.musicmind.theory.tuning.system.TuningSystem

data class DiscretePitchInstrumentImpl internal constructor(
    override val keys: Int,
    override val tuningSystem: TuningSystem,
    override val tuning: InstrumentTuning,
    private val pitchProducer: PitchProducer,
    private val pitchClassifier: PitchClassifier,
) : AbstractDiscretePitchInstrument(pitchClassifier) {

    override val notes by lazy {
        with(pitchProducer) { pitches() }
            .mapIndexed { i, pitch -> Note(key = Key(ordinal = i + 1), pitch = pitch) }
    }

    override fun Note.scale(partition: OctavePartition): FiniteNoteScale? {
        if (!exists) return null
        tuningSystem.verify(partition)
        val notes = mutableListOf(this)
        var lastNote = this
        // iterate over distances without last "looping" one
        for (i in 0 until partition.distribution.size - 1) {
            val note = lastNote + partition.distribution[i]
            if (note != null) {
                notes.add(note)
                lastNote = note
            } else {
                break
            }
        }
        return FiniteNoteScale(entries = notes)
    }

}