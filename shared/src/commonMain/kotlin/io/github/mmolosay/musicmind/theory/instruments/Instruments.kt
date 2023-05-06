package io.github.mmolosay.musicmind.theory.instruments

import io.github.mmolosay.musicmind.theory.instruments.discrete.DiscretePitchInstrumentImpl
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.FretboardKeys
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.FretboardKeys.KeyGroup
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.Key
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.KeyboardKeys
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.key
import io.github.mmolosay.musicmind.theory.pitch.PitchClassifier
import io.github.mmolosay.musicmind.theory.tuning.KeysTuner
import io.github.mmolosay.musicmind.theory.tuning.instrument.FretboardTuning
import io.github.mmolosay.musicmind.theory.tuning.instrument.KeyboardTuning
import io.github.mmolosay.musicmind.theory.tuning.instrument.Tunings
import io.github.mmolosay.musicmind.theory.tuning.system.TuningSystem
import io.github.mmolosay.musicmind.theory.tuning.system.TuningSystems

@Suppress("FunctionName")
class Instruments internal constructor(
    private val defaultKeysTuner: KeysTuner,
    private val defaultPitchClassifier: PitchClassifier,
) {

    fun Piano(
        keys: Int = DefaultPianoKeys,
        tuningSystem: TuningSystem = TuningSystems.EqualTemperament12Tone(),
        instrumentTuning: KeyboardTuning = Tunings.ConcertPiano(),
        keysTuner: KeysTuner = defaultKeysTuner,
        pitchClassifier: PitchClassifier = defaultPitchClassifier,
    ): DiscretePitchInstrument {
        val keyboardKeys = Keys.Keyboard(size = keys)
        val notes = with(keysTuner) { keyboardKeys.tune(tuningSystem, instrumentTuning) }
        return DiscretePitchInstrumentImpl(
            keys = keyboardKeys,
            notes = notes,
            tuningSystem = tuningSystem,
            tuning = instrumentTuning,
            pitchClassifier = pitchClassifier,
        )
    }

    fun Guitar(
        strings: Int = DefaultGuitarStrings,
        fretsPerString: Int = DefaultGuitarFretsPerString,
        flageoletsPerString: Int = DefaultGuitarFlageoletsPerString,
        tuningSystem: TuningSystem = TuningSystems.EqualTemperament12Tone(),
        instrumentTuning: FretboardTuning = Tunings.Guitar.StandardTuning,
        keysTuner: KeysTuner = defaultKeysTuner,
        pitchClassifier: PitchClassifier = defaultPitchClassifier,
    ): DiscretePitchInstrument {
        val fretboardKeys = Keys.Fretboard(strings, fretsPerString, flageoletsPerString)
        val notes = with(keysTuner) { fretboardKeys.tune(tuningSystem, instrumentTuning) }
        return DiscretePitchInstrumentImpl(
            keys = fretboardKeys,
            notes = notes,
            tuningSystem = tuningSystem,
            tuning = instrumentTuning,
            pitchClassifier = pitchClassifier,
        )
    }

    object Keys {

        fun Keyboard(size: Int): KeyboardKeys =
            KeyboardKeys(
                all = List(size) { i -> Key(ordinal = i + 1) },
            )

        fun Fretboard(
            strings: Int,
            fretsPerString: Int,
            flageoletsPerString: Int,
        ): FretboardKeys =
            FretboardKeys(
                sets = buildList {
                    repeat(times = strings) {
                        makeFretboardKeySet(
                            lastKeyOrdinal = lastOrNull()?.lastOrNull()?.last()?.ordinal ?: 0,
                            fretsPerString = fretsPerString,
                            flageoletsPerString = flageoletsPerString,
                        ).also { add(it) }
                    }
                },
            )

        private fun makeFretboardKeySet(
            lastKeyOrdinal: Int,
            fretsPerString: Int,
            flageoletsPerString: Int,
        ): FretboardKeys.KeySet {
            var ordinal = lastKeyOrdinal + 1
            val openString = KeyGroup.Single(
                type = KeyGroup.Type.StringOpen,
                key = ordinal++.key,
            )
            val frets = KeyGroup.Multiple(
                type = KeyGroup.Type.StringFrets,
                keys = List(fretsPerString) {ordinal++.key },
            )
            // TODO: resolve
//            val flageolets = KeyGroup.Multiple(
//                type = KeyGroup.Type.StringFlageolets,
//                keys = List(flageoletsPerString) { i -> (ordinal++ + i).key },
//            )
            return FretboardKeys.KeySet(listOf(openString, frets /*flageolets*/))
        }
    }

    companion object {
        private const val DefaultPianoKeys = 88

        private const val DefaultGuitarStrings = 6
        private const val DefaultGuitarFretsPerString = 22
        private const val DefaultGuitarFlageoletsPerString = 7
    }
}