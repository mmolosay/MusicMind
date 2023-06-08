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
    private val keysTuner: KeysTuner,
    private val defaultPitchClassifier: PitchClassifier,
) {

    fun Piano(
        keys: KeyboardKeys = KeysFactory.Keyboard(),
        tuningSystem: TuningSystem = TuningSystems.Tet12(),
        instrumentTuning: KeyboardTuning = Tunings.ConcertPiano(),
        pitchClassifier: PitchClassifier = defaultPitchClassifier,
    ): DiscretePitchInstrument<KeyboardKeys> =
        DiscretePitchInstrumentImpl(
            keys = keys,
            notes = with(keysTuner) { keys.tune(tuningSystem, instrumentTuning) },
            tuningSystem = tuningSystem,
            tuning = instrumentTuning,
            pitchClassifier = pitchClassifier,
        )

    fun Guitar(
        strings: Int = DefaultGuitarStrings,
        fretsPerString: Int = DefaultGuitarFretsPerString,
        flageoletsPerString: Int = DefaultGuitarFlageoletsPerString,
        tuningSystem: TuningSystem = TuningSystems.Tet12(),
        instrumentTuning: FretboardTuning = Tunings.Guitar.StandardTuning,
        pitchClassifier: PitchClassifier = defaultPitchClassifier,
    ): DiscretePitchInstrument<FretboardKeys> =
        Guitar(
            keys = KeysFactory.Fretboard(strings, fretsPerString, flageoletsPerString),
            tuningSystem = tuningSystem,
            instrumentTuning = instrumentTuning,
            pitchClassifier = pitchClassifier,
        )

    fun Guitar(
        keys: FretboardKeys,
        tuningSystem: TuningSystem,
        instrumentTuning: FretboardTuning,
        pitchClassifier: PitchClassifier = defaultPitchClassifier,
    ): DiscretePitchInstrument<FretboardKeys> =
        DiscretePitchInstrumentImpl(
            keys = keys,
            notes = with(keysTuner) { keys.tune(tuningSystem, instrumentTuning) },
            tuningSystem = tuningSystem,
            tuning = instrumentTuning,
            pitchClassifier = pitchClassifier,
        )

    companion object {
        private const val DefaultGuitarStrings = 6
        private const val DefaultGuitarFretsPerString = 22
        private const val DefaultGuitarFlageoletsPerString = 7
    }
}