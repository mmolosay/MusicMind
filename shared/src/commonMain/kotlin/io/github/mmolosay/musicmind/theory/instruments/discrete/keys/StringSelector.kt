package io.github.mmolosay.musicmind.theory.instruments.discrete.keys

import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.FretboardKeys.KeyGroup

sealed interface StringSelector {

    val keys: FretboardKeys

    class ByOrdinal internal constructor(
        val ordinal: Int,
        override val keys: FretboardKeys,
    ) : StringSelector
}

fun FretboardKeys.string(ordinal: Int): StringSelector =
    StringSelector.ByOrdinal(ordinal = ordinal, keys = this)

fun StringSelector.open(): Key =
    when (this) {
        is StringSelector.ByOrdinal -> open()
    }

fun StringSelector.fret(ordinal: Int): Key =
    when (this) {
        is StringSelector.ByOrdinal -> fret(ordinal)
    }

private fun StringSelector.ByOrdinal.open(): Key =
    keys.strings[this.ordinal - 1]
        .first { it.type == KeyGroup.Type.OpenString }
        .single()

fun StringSelector.ByOrdinal.fret(ordinal: Int): Key =
    keys.strings[this.ordinal - 1]
        .first { it.type == KeyGroup.Type.StringFrets }
        .get(ordinal - 1)