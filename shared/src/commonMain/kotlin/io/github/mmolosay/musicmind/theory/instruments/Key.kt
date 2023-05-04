package io.github.mmolosay.musicmind.theory.instruments

/**
 * Represents a specific position on an instrument.
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