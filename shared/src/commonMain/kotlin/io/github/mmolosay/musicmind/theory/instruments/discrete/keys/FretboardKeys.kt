package io.github.mmolosay.musicmind.theory.instruments.discrete.keys

import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.FretboardKeys.KeyGroup

typealias StringGroups = List<KeyGroup>
typealias Strings = List<StringGroups>

class FretboardKeys internal constructor(
    val strings: Strings,
) : Keys {

    override val total: Int by lazy {
        strings.sumOf { it.total }
    }

    override fun with(ordinal: Int): Key? {
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
        return strings.flatten().flatten().getOrNull(ordinal - 1)
    }

    class KeyGroup(
        val type: Type,
        keys: List<Key>,
    ) : List<Key> by keys {

        constructor(type: Type, key: Key) : this(type, listOf(key))

        enum class Type {
            OpenString,
            StringFret,
            StringFlageolet,
        }
    }

    val StringGroups.total: Int
        get() = sumOf { it.size }
}