package io.github.mmolosay.musicmind.theory.instruments

import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.FretboardKeys
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.Key
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.KeyboardKeys
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.KeyboardLayout
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.KeyboardLayouts
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.StringGroups

@Suppress("FunctionName")
object KeysFactory {

    fun Keyboard(
        amount: Int = 88,
        layout: KeyboardLayout = KeyboardLayouts.For12PitchClasses(),
    ): KeyboardKeys {
        val keys = List(amount) { i ->
            val ordinal = i + 1
            Key(
                ordinal = ordinal,
                rank = layout.rankFor(ordinal)
            )
        }
        return KeyboardKeys(keys)
    }

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
    ): StringGroups {
        var ordinal = startOrdinal + 1
        val openString = FretboardKeys.KeyGroup(
            type = FretboardKeys.KeyGroup.Type.OpenString,
            key = Key(ordinal++, Key.Rank(0)),
        )
        val frets = FretboardKeys.KeyGroup(
            type = FretboardKeys.KeyGroup.Type.StringFret,
            keys = List(fretsPerString) { Key(ordinal++, Key.Rank(1)) },
        )
        // TODO: resolve
//            val flageolets = KeyGroup.Multiple(
//                type = KeyGroup.Type.StringFlageolets,
//                keys = List(flageoletsPerString) { i -> (ordinal++ + i).key },
//            )
        return listOf(openString, frets/*, flageolets*/)
    }
}