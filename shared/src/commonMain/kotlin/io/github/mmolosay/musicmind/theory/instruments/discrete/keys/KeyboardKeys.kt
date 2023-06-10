package io.github.mmolosay.musicmind.theory.instruments.discrete.keys

class KeyboardKeys internal constructor(
    val list: List<KeyboardKey>,
) : InstrumentKeys<KeyboardKey>() {

    override val total: Int = list.size

    override fun key(ordinal: Int): KeyboardKey =
        list[ordinal - 1]
}

data class KeyboardKey(
    override val ordinal: Int,
    val rank: Int,
) : InstrumentKey

fun KeyboardKeys.range(startOrdinalInclusive: Int, keys: Int): List<KeyboardKey> {
    val startIndex = startOrdinalInclusive - 1
    val range = startIndex until startIndex + keys
    return list.slice(range)
}

val List<KeyboardKey>.maxRank: Int
    get() = maxOf { it.rank }

fun List<KeyboardKey>.naturals(): List<KeyboardKey> =
    filter { it.isNatural }

fun List<KeyboardKey>.accidentals(): List<List<KeyboardKey>> {
    val ranks = List(size = maxRank) { mutableListOf<KeyboardKey>() }
    for (key in this) {
        if (key.isNatural) continue
        ranks[key.rank - 1] += key
    }
    return ranks
}

fun List<KeyboardKey>.accidentalsOf(rankTier: Int): List<KeyboardKey> =
    filter { it.rank == rankTier }

val KeyboardKey.isNatural: Boolean
    get() = (rank == 0)

val KeyboardKey.isAccidental: Boolean
    get() = !isNatural
