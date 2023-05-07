package io.github.mmolosay.musicmind.theory.instruments.discrete.keys

@JvmInline
value class FretboardKeys internal constructor(
    val strings: Strings,
) : Keys {

    override val total: Int
        get() = strings.sumOf { it.total }

    class KeyGroup(
        val type: Type,
        keys: List<Key>,
    ) : List<Key> by keys {

        constructor(type: Type, key: Key) : this(type, listOf(key))

        enum class Type {
            OpenString,
            StringFrets,
            StringFlageolets,
        }
    }

    val StringKeys.total: Int
        get() = sumOf { it.size }
}

typealias Strings = List<StringKeys>

typealias StringKeys = List<FretboardKeys.KeyGroup>