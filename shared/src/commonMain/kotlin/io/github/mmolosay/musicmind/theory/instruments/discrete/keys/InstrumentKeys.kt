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