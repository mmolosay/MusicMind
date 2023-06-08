package io.github.mmolosay.musicmind.theory.instruments.discrete.keys

/**
 * A set that containing all keys of some instrument without duplicates.
 */
sealed class InstrumentKeys<K : InstrumentKey> {

    abstract val total: Int

    abstract fun with(ordinal: Int): K

    fun contains(ordinal: Int): Boolean =
        (ordinal in 1..total)
}

fun <K : InstrumentKey> InstrumentKeys<K>.withOrNull(ordinal: Int): K? =
    if (!contains(ordinal)) null else with(ordinal)

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