package io.github.mmolosay.musicmind.theory.context

import io.github.mmolosay.musicmind.theory.instruments.Instrument

internal data class MusicContextImpl<out I : Instrument>(
    override val utils: MusicContext.Utils,
    override val instrument: I,
) : MusicContext<I>