package io.github.mmolosay.musicmind.theory.tuning.instrument

import io.github.mmolosay.musicmind.theory.pitch.Pitches

@Suppress("FunctionName")
object Tunings {

    fun ConcertPiano(): PianoTuning =
        PianoTuning(
            a4Frequency = Pitches.A4ConcertPitch.frequency.toBigDecimal(),
        )
}