package io.github.mmolosay.musicmind.theory.instruments

import io.github.mmolosay.musicmind.theory.context.MusicContext
import io.github.mmolosay.musicmind.theory.instruments.discrete.DiscretePitchInstrumentImpl
import io.github.mmolosay.musicmind.theory.pitch.PitchClassifier
import io.github.mmolosay.musicmind.theory.tuning.PitchProducer
import io.github.mmolosay.musicmind.theory.tuning.PitchSequencer
import io.github.mmolosay.musicmind.theory.tuning.instrument.InstrumentTuning
import io.github.mmolosay.musicmind.theory.tuning.instrument.Tunings
import io.github.mmolosay.musicmind.theory.tuning.system.TuningSystem
import io.github.mmolosay.musicmind.theory.tuning.system.TuningSystems

@Suppress("FunctionName")
class Instruments(
    private val utils: MusicContext.Utils,
) {

    fun Piano(
        keys: Int = PianoFullSizeKeys,
        tuningSystem: TuningSystem = TuningSystems.EqualTemperament12Tone(),
        instrumentTuning: InstrumentTuning = Tunings.ConcertPiano(),
        pitchSequencer: PitchSequencer = utils.pitchSequencer,
        pitchClassifier: PitchClassifier = utils.pitchClassifier,
    ): DiscretePitchInstrument =
        DiscretePitchInstrumentImpl(
            keys = keys,
            tuningSystem = tuningSystem,
            tuning = instrumentTuning,
            pitchProducer = PitchProducer(pitchSequencer),
            pitchClassifier = pitchClassifier,
        )

    companion object {
        private const val PianoFullSizeKeys = 88
    }
}