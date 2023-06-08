package io.github.mmolosay.musicmind.theory.instruments.discrete.keys

fun FretboardKeys.string(ordinal: Int): StringGroups =
    strings[ordinal - 1]

fun StringGroups.of(type: FretboardKeys.KeyGroup.Type): FretboardKeys.KeyGroup =
    first { it.type == type }

fun StringGroups.open(): Key =
    this
        .of(FretboardKeys.KeyGroup.Type.OpenString)
        .single()

fun StringGroups.fret(ordinalInString: Int): Key =
    this
        .of(FretboardKeys.KeyGroup.Type.StringFret)
        .get(ordinalInString - 1)