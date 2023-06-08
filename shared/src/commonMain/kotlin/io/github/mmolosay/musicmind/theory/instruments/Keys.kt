package io.github.mmolosay.musicmind.theory.instruments

import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.FretboardKey
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.FretboardKeys
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.KeyboardKey
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.KeyboardKeys
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.KeyboardLayout
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.KeyboardLayouts
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.StringKeys

@Suppress("FunctionName")
object Keys {

    fun Keyboard(
        amount: Int = 88,
        firstOctaveOffset: Int = 9,
        layout: KeyboardLayout = KeyboardLayouts.For12PitchClasses(),
    ): KeyboardKeys =
        List(amount) { i ->
            val ordinal = i + 1
            KeyboardKey(
                ordinal = ordinal,
                rank = layout.rankFor(ordinal + firstOctaveOffset),
            )
        }.let { KeyboardKeys(keys = it) }

    fun Fretboard(
        strings: Int,
        fretsPerString: Int,
        flageoletsPerString: Int,
    ): FretboardKeys =
        buildList<StringKeys> {
            repeat(times = strings) {
                makeFretboardStringKeys(
                    lastUsedOrdinal = lastOrNull()?.last()?.ordinal ?: 0,
                    fretsPerString = fretsPerString,
                    flageoletsPerString = flageoletsPerString,
                ).also { add(it) }
            }
        }.let {
            FretboardKeys(strings = it)
        }

    private fun makeFretboardStringKeys(
        lastUsedOrdinal: Int,
        fretsPerString: Int,
        flageoletsPerString: Int,
    ): StringKeys =
        buildList(capacity = 1 + fretsPerString/* + flageoletsPerString*/) {
            var ordinal = lastUsedOrdinal + 1
            FretboardKey(
                ordinal = ordinal++,
                type = FretboardKey.Type.OpenString,
            ).also { add(it) }
            repeat(fretsPerString) {
                FretboardKey(
                    ordinal = ordinal++,
                    type = FretboardKey.Type.StringFret,
                ).also { add(it) }
            }
            // TODO: flageolets go here
        }
}