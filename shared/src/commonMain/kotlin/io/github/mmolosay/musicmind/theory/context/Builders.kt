package io.github.mmolosay.musicmind.theory.context

import io.github.mmolosay.musicmind.theory.instruments.Instrument
import io.github.mmolosay.musicmind.theory.instruments.Instruments
import io.github.mmolosay.musicmind.theory.pitch.DefaultPitchClassifier
import io.github.mmolosay.musicmind.theory.pitch.PitchClassifier
import io.github.mmolosay.musicmind.theory.tuning.DefaultPitchSequencer
import io.github.mmolosay.musicmind.theory.tuning.KeysTuner
import io.github.mmolosay.musicmind.theory.tuning.PitchSequencer

fun <I : Instrument> MusicContext(
    pitchSequencer: PitchSequencer = DefaultPitchSequencer(),
    pitchClassifier: PitchClassifier = DefaultPitchClassifier(),
    instrument: Instruments.() -> I,
): MusicContext<I> {
    val utils = MusicContext.Utils(
        pitchSequencer = pitchSequencer,
        pitchClassifier = pitchClassifier,
    )
    return MusicContextImpl(
        utils = utils,
        instrument = Instruments(
            defaultKeysTuner = KeysTuner(pitchSequencer),
            defaultPitchClassifier = pitchClassifier,
        ).instrument(),
    )
}