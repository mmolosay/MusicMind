package io.github.mmolosay.musicmind.theory.instruments.discrete.keys

/**
 * A set that containing all keys of some instrument without duplicates.
 * A strategy of obtaining instrument's keys in is provided by the instrument itself.
 */
sealed interface Keys {
    val total: Int
    fun with(ordinal: Int): Key?
}