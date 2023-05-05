package io.github.mmolosay.musicmind.theory.context

import io.github.mmolosay.musicmind.theory.instruments.Instrument
import io.github.mmolosay.musicmind.theory.tuning.PitchSequencer

/**
 * A music context is an environment, in which music happens.
 * It is a collection of all the factors, that contribute to the final sound being produced.
 */
interface MusicContext<out I : Instrument> {
    val utils: Utils
    val instrument: I

    data class Utils(
        val pitchSequencer: PitchSequencer,
    )
}