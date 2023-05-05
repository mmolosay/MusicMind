package io.github.mmolosay.musicmind.theory.context

import io.github.mmolosay.musicmind.theory.instruments.Instrument
import io.github.mmolosay.musicmind.theory.instruments.Instruments
import io.github.mmolosay.musicmind.theory.tuning.DefaultPitchSequencer
import io.github.mmolosay.musicmind.theory.tuning.PitchSequencer

fun <I : Instrument> MusicContext(
    pitchSequencer: PitchSequencer = DefaultPitchSequencer(),
    instrument: Instruments.() -> I,
): MusicContext<I> {
    val utils = MusicContext.Utils(
        pitchSequencer = pitchSequencer,
    )
    return MusicContextImpl(
        utils = utils,
        instrument = Instruments(utils).instrument(),
    )
}