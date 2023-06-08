package io.github.mmolosay.musicmind.theory.instruments.discrete.keys

fun FretboardKeys.string(ordinal: Int): StringKeys =
    strings[ordinal - 1]

fun StringKeys.open(): InstrumentKey =
    first { it.type == FretboardKey.Type.OpenString }

fun StringKeys.fret(ordinalInString: Int): InstrumentKey =
    this
        .filter { it.type == FretboardKey.Type.StringFret }
        .get(ordinalInString - 1)