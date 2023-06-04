package io.github.mmolosay.musicmind.theory.instruments

import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.FretboardKeys
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.Key
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.KeyboardKeys
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.StringKeys
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.key

@Suppress("FunctionName")
object KeysFactory {

    fun Keyboard(size: Int): KeyboardKeys =
        KeyboardKeys(
            keys = List(size) { i -> Key(ordinal = i + 1) },
        )

    fun Fretboard(
        strings: Int,
        fretsPerString: Int,
        flageoletsPerString: Int,
    ): FretboardKeys =
        FretboardKeys(
            strings = buildList {
                repeat(times = strings) {
                    makeFretboardStringKeys(
                        startOrdinal = lastOrNull()?.lastOrNull()?.last()?.ordinal ?: 0,
                        fretsPerString = fretsPerString,
                        flageoletsPerString = flageoletsPerString,
                    ).also { add(it) }
                }
            },
        )

    private fun makeFretboardStringKeys(
        startOrdinal: Int,
        fretsPerString: Int,
        flageoletsPerString: Int,
    ): StringKeys {
        var ordinal = startOrdinal + 1
        val openString = FretboardKeys.KeyGroup(
            type = FretboardKeys.KeyGroup.Type.OpenString,
            key = ordinal++.key,
        )
        val frets = FretboardKeys.KeyGroup(
            type = FretboardKeys.KeyGroup.Type.StringFret,
            keys = List(fretsPerString) { ordinal++.key },
        )
        // TODO: resolve
//            val flageolets = KeyGroup.Multiple(
//                type = KeyGroup.Type.StringFlageolets,
//                keys = List(flageoletsPerString) { i -> (ordinal++ + i).key },
//            )
        return listOf(openString, frets/*, flageolets*/)
    }
}