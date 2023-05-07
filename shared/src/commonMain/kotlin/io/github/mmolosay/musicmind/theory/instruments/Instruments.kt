package io.github.mmolosay.musicmind.theory.instruments

import io.github.mmolosay.musicmind.theory.instruments.discrete.DiscretePitchInstrumentImpl
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.FretboardKeys
import io.github.mmolosay.musicmind.theory.instruments.discrete.keys.KeyboardKeys
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
    ): DiscretePitchInstrument<KeyboardKeys> =
        Piano(
            keys = KeysFactory.Keyboard(keys),
            tuningSystem = tuningSystem,
            instrumentTuning = instrumentTuning,
            keysTuner = keysTuner,
            pitchClassifier = pitchClassifier,
        )

    fun Piano(
        keys: KeyboardKeys,
        tuningSystem: TuningSystem,
        instrumentTuning: KeyboardTuning,
        keysTuner: KeysTuner = defaultKeysTuner,
        pitchClassifier: PitchClassifier = defaultPitchClassifier,
    ): DiscretePitchInstrument<KeyboardKeys> {
        val notes = with(keysTuner) { keys.tune(tuningSystem, instrumentTuning) }
        return DiscretePitchInstrumentImpl(
            keys = keys,
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
    ): DiscretePitchInstrument<FretboardKeys> =
        Guitar(
            keys = KeysFactory.Fretboard(strings, fretsPerString, flageoletsPerString),
            tuningSystem = tuningSystem,
            instrumentTuning = instrumentTuning,
            keysTuner = keysTuner,
            pitchClassifier = pitchClassifier,
        )

    fun Guitar(
        keys: FretboardKeys,
        tuningSystem: TuningSystem,
        instrumentTuning: FretboardTuning,
        keysTuner: KeysTuner = defaultKeysTuner,
        pitchClassifier: PitchClassifier = defaultPitchClassifier,
    ): DiscretePitchInstrument<FretboardKeys> {
        val notes = with(keysTuner) { keys.tune(tuningSystem, instrumentTuning) }
        return DiscretePitchInstrumentImpl(
            keys = keys,
            notes = notes,
            tuningSystem = tuningSystem,
            tuning = instrumentTuning,
            pitchClassifier = pitchClassifier,
        )
    }

    companion object {
        private const val DefaultPianoKeys = 88

        private const val DefaultGuitarStrings = 6
        private const val DefaultGuitarFretsPerString = 22
        private const val DefaultGuitarFlageoletsPerString = 7
    }
}