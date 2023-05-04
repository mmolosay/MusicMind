package io.github.mmolosay.musicmind.theory.context

import io.github.mmolosay.musicmind.theory.instruments.Instrument
import io.github.mmolosay.musicmind.theory.perception.Pitch
import io.github.mmolosay.musicmind.theory.scales.Scale

/**
 * A music context is an environment, in which music happens.
 * It is a collection of all the factors, that adds to final sound being produced.
 */
interface MusicContext {
    val instrument: Instrument

    fun Scale.pitches(): List<Pitch>
}