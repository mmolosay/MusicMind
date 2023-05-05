package io.github.mmolosay.musicmind.theory.context

import io.github.mmolosay.musicmind.theory.instruments.Instrument
import io.github.mmolosay.musicmind.theory.instruments.Instruments
import io.github.mmolosay.musicmind.theory.tuning.DefaultPitchSequencer
import io.github.mmolosay.musicmind.theory.tuning.PitchSequencer

fun MusicContext(
    pitchSequencer: PitchSequencer = DefaultPitchSequencer(),
    instrument: Instruments.() -> Instrument,
): MusicContext {
    val utils = MusicContext.Utils(
        pitchSequencer = pitchSequencer,
    )
    return MusicContextImpl(
        utils = utils,
        instrument = Instruments(utils).instrument(),
    )
}