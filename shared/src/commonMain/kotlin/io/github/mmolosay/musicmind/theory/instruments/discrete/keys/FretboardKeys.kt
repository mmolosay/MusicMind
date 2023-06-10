package io.github.mmolosay.musicmind.theory.instruments.discrete.keys

typealias StringKeys = List<FretboardKey>

class FretboardKeys internal constructor(
    val strings: List<StringKeys>,
) : InstrumentKeys<FretboardKey>() {

    override val total: Int by lazy {
        strings.sumOf { it.size }
    }

    override fun key(ordinal: Int): FretboardKey {
//        val index = ordinal - 1
//        var passed = 0
//        var maybePassed = 0
//        for (groups in strings) {
//            maybePassed = passed + groups.total
//            if (maybePassed >= ordinal) {
//                return groups.
//            }
//            passed = maybePassed
//        }
        return strings.flatten()[ordinal - 1]
    }

    override fun iterator(): Iterator<FretboardKey> =
        object : Iterator<FretboardKey> {

            val i = 0

            override fun hasNext(): Boolean {
                TODO("Not yet implemented")
            }

            override fun next(): FretboardKey {
                TODO("Not yet implemented")
            }
        }
}

data class FretboardKey(
    override val ordinal: Int,
    val type: Type,
) : InstrumentKey {

    enum class Type {
        OpenString,
        StringFret,
        StringFlageolet,
    }
}