package io.github.mmolosay.musicmind.theory.instruments

import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.Key
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.Keys
import io.github.mmolosay.musicmind.theory.partition.OctavePartition
import io.github.mmolosay.musicmind.theory.pitch.Pitch
import io.github.mmolosay.musicmind.theory.pitch.PitchClass
import io.github.mmolosay.musicmind.theory.scales.FiniteKeyScale
import io.github.mmolosay.musicmind.theory.scales.FinitePitchScale
import io.github.mmolosay.musicmind.theory.tuning.system.TuningSystem

/**
 * Discrete pitch instruments are those that are made to produce pitches in discrete steps.
 * Examples of such instruments are piano, xylophone, giutars (not every tuning).
 *
 * [Chromatic scale, musical instruments – Wikipedia](https://en.wikipedia.org/wiki/Diatonic_and_chromatic#Musical_instruments)
 */
interface DiscretePitchInstrument : Instrument {

    val keys: Keys
    val tuningSystem: TuningSystem
    val notes: Map<Key, Pitch> //
    val pitchClasses: List<PitchClass>

    val Key.exists: Boolean

    infix fun Key.scale(partition: OctavePartition): FiniteKeyScale?
    fun FiniteKeyScale.pitches(): FinitePitchScale

    data class Note internal constructor(
        val key: Key,
        val pitch: Pitch,
    ) : Comparable<Note> {

        override fun compareTo(other: Note): Int =
            this.key.compareTo(other.key)
    }

}