package io.github.mmolosay.musicmind.theory.instruments.discrete.keys

/**
 * Represents a specific physical position on a discrete pitch instrument.
 * It is a key on a piano and fret of string on a guitar.
 */
class Key internal constructor(
    val ordinal: Int,
    val rank: Rank,
) : Comparable<Key> {

    init {
        require(ordinal > 0) { "Key ordinal must be greater than zero" }
    }

    override fun compareTo(other: Key): Int =
        this.ordinal.compareTo(other.ordinal)

    @JvmInline
    value class Rank internal constructor(val tier: Int)
}