package io.github.mmolosay.musicmind.theory.instruments

import io.github.mmolosay.musicmind.theory.instruments.DiscretePitchInstrument.Key
import io.github.mmolosay.musicmind.theory.instruments.DiscretePitchInstrument.Note
import io.github.mmolosay.musicmind.theory.partition.OctavePartition
import io.github.mmolosay.musicmind.theory.pitch.Pitch
import io.github.mmolosay.musicmind.theory.pitch.PitchClass
import io.github.mmolosay.musicmind.theory.scales.FiniteNoteScale
import io.github.mmolosay.musicmind.theory.tuning.system.TuningSystem

/**
 * Discrete pitch instruments are those that are made to produce pitches in discrete steps.
 * Examples of such instruments are piano, xylophone, giutars (not every tuning).
 *
 * [Chromatic scale, musical instruments – Wikipedia](https://en.wikipedia.org/wiki/Diatonic_and_chromatic#Musical_instruments)
 */
interface DiscretePitchInstrument : Instrument {

    val keys: Int
    val tuningSystem: TuningSystem
    val notes: List<Note>
    val pitchClasses: List<PitchClass>

    val Note.exists: Boolean

    fun Note.scale(partition: OctavePartition): FiniteNoteScale?

    data class Note internal constructor(
        val key: Key,
        val pitch: Pitch,
    ) : Comparable<Note> {

        override fun compareTo(other: Note): Int =
            this.key.compareTo(other.key)
    }

    /**
     * Represents a specific physical position on an instrument.
     * It is a key on a piano and fret of string on a guitar.
     *
     * A strategy of obtaining a set of all instrument's keys in correct order is provided by the instrument itself.
     */
    @JvmInline
    value class Key internal constructor(val ordinal: Int) : Comparable<Key> {

        init {
            require(ordinal > 0) { "Key ordinal must be greater than zero" }
        }

        override fun compareTo(other: Key): Int =
            this.ordinal.compareTo(other.ordinal)
    }
}

// region DiscretePitchInstrument extensions

infix fun DiscretePitchInstrument.canProduce(pitch: Pitch): Boolean =
    notes.any { it.pitch == pitch }

infix fun DiscretePitchInstrument.noteAt(key: Key): Note =
    notes.first { it.key == key }

// endregion

// region Key extensions

val Int.key: Key
    get() = Key(ordinal = this)

// endregion