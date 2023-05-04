package io.github.mmolosay.musicmind.theory.context

import io.github.mmolosay.musicmind.theory.instruments.Instrument

fun MusicContext(
    instrument: Instrument,
): MusicContext =
    MusicContextImpl(
        instrument = instrument,
    )