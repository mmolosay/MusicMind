package io.github.mmolosay.musicmind.theory.instruments.discrete.keys

import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.FretboardKeys.KeyGroup

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

typealias StringKeys = List<KeyGroup>

// region Selectors

fun FretboardKeys.string(ordinal: Int): StringKeys =
    strings[ordinal - 1]

fun StringKeys.of(type: KeyGroup.Type): KeyGroup =
    first { it.type == type }

fun StringKeys.open(): Key =
    this
        .of(KeyGroup.Type.OpenString)
        .single()


fun StringKeys.fret(ordinal: Int): Key =
    this
        .of(KeyGroup.Type.StringFrets)
        .get(ordinal - 1)

// endregion