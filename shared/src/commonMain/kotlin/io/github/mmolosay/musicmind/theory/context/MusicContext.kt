package io.github.mmolosay.musicmind.theory.context

import io.github.mmolosay.musicmind.theory.instruments.DiscretePitchInstrument
import io.github.mmolosay.musicmind.theory.instruments.ContinuousPitchInstrument
import io.github.mmolosay.musicmind.theory.instruments.Instrument
import io.github.mmolosay.musicmind.theory.pitch.Pitch
import io.github.mmolosay.musicmind.theory.scales.Scale
import io.github.mmolosay.musicmind.theory.tuning.PitchSequencer

/**
 * A music context is an environment, in which music happens.
 * It is a collection of all the factors, that contribute to the final sound being produced.
 */
interface MusicContext {
    val utils: Utils
    val instrument: Instrument

    fun Scale.pitches(): List<Pitch>

    data class Utils(
        val pitchSequencer: PitchSequencer,
    )
}

val MusicContext.instrumentAsChromatic: DiscretePitchInstrument?
    get() = (instrument as? DiscretePitchInstrument)

val MusicContext.instrumentAsContiuousPitch: ContinuousPitchInstrument?
    get() = (instrument as? ContinuousPitchInstrument)