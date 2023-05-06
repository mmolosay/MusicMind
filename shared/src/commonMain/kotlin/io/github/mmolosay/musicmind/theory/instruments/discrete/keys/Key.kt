package io.github.mmolosay.musicmind.theory.instruments.discrete.keys

/**
 * Represents a specific physical position on a discrete pitch instrument.
 * It is a key on a piano and fret of string on a guitar.
 */
@JvmInline
value class Key internal constructor(val ordinal: Int) : Comparable<Key> {

    init {
        require(ordinal > 0) { "Key ordinal must be greater than zero" }
    }

    override fun compareTo(other: Key): Int =
        this.ordinal.compareTo(other.ordinal)
}

val Int.key: Key
    get() = Key(ordinal = this)