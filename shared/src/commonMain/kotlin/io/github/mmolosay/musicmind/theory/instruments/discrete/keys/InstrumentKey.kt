package io.github.mmolosay.musicmind.theory.instruments.discrete.keys

/**
 * Represents a specific physical position on a discrete pitch instrument.
 * It is a key on a piano and fret of string on a guitar.
 *
 * Its [ordinal] property contains absolute ordinal of this key among other keys of this instrument.
 * So, keyboard key with ordinal = 49 would be exactly 49th key of this instrument.
 *
 * In terms of one instrument, each key has a unique [ordinal].
 */
sealed interface InstrumentKey : Comparable<InstrumentKey> {

    val ordinal: Int

    override fun compareTo(other: InstrumentKey): Int =
        this.ordinal.compareTo(other.ordinal)
}

