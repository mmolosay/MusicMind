package io.github.mmolosay.musicmind.theory.instruments

import io.github.mmolosay.musicmind.theory.distance.Distance
import io.github.mmolosay.musicmind.theory.pitch.Pitch
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

    val Note.exists: Boolean

    fun Note.scale(intervals: List<Distance>): List<Note>?

    data class Note internal constructor(
        val key: Key,
        val pitch: Pitch,
    )

    /**
     * Represents a specific physical position on an instrument.
     * It is a key on a piano and fret of string on a guitar.
     *
     * A strategy of obtaining a set of all instrument's keys in correct order is provided by the instrument itself.
     */
    @JvmInline
    value class Key(val ordinal: Int) {
        init {
            require(ordinal > 0) { "Key ordinal must be greater than zero" }
        }
    }
}