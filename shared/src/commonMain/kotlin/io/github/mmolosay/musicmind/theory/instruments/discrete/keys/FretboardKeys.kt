package io.github.mmolosay.musicmind.theory.instruments.discrete.keys

@JvmInline
value class FretboardKeys internal constructor(
    val sets: List<KeySet>,
) : Keys {

    override val total: Int
        get() = sets.sumOf { it.total }

    class KeySet(list: List<KeyGroup>) : List<KeyGroup> by list

    sealed interface KeyGroup : List<Key> {

        val type: Type

        class Single internal constructor(
            override val type: Type,
            val key: Key,
        ) : AbstractList<Key>(), KeyGroup {
            override val size: Int = 1
            override fun get(index: Int): Key = key
        }

        class Multiple internal constructor(
            override val type: Type,
            val keys: List<Key>,
        ) : List<Key> by keys, KeyGroup

        enum class Type {
            StringOpen,
            StringFrets,
            StringFlageolets,
        }
    }

    val KeySet.total: Int
        get() = sumOf { group -> group.total }

    val KeyGroup.total: Int
        get() = when (this) {
            is KeyGroup.Single -> 1
            is KeyGroup.Multiple -> keys.size
        }
}