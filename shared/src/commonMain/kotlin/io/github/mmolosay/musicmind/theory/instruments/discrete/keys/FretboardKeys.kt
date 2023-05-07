package io.github.mmolosay.musicmind.theory.instruments.discrete.keys

@JvmInline
value class FretboardKeys internal constructor(
    val strings: List<StringKeys>,
) : Keys {

    override val total: Int
        get() = strings.sumOf { it.total }

    class StringKeys(groups: List<KeyGroup>) : List<KeyGroup> by groups {
        // TODO: ordinal?
    }

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