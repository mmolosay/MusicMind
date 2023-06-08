package io.github.mmolosay.musicmind.theory.instruments.discrete.keys

class KeyboardKeys internal constructor(
    val keys: List<KeyboardKey>,
) : InstrumentKeys<KeyboardKey>() {

    override val total: Int = keys.size

    override fun with(ordinal: Int): KeyboardKey =
        keys[ordinal - 1]
}

class KeyboardKey(
    override val ordinal: Int,
    val rank: Rank,
) : InstrumentKey {

    // TODO: abolish Rank if there's no additional funstionality added to this class
    @JvmInline
    value class Rank internal constructor(val tier: Int)
}