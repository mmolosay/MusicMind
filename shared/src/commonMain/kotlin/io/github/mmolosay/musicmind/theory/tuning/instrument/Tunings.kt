package io.github.mmolosay.musicmind.theory.tuning.instrument

import io.github.mmolosay.musicmind.theory.pitch.Pitch
import io.github.mmolosay.musicmind.theory.pitch.hz

@Suppress("FunctionName")
object Tunings {

    fun ConcertPiano(): KeyboardTuning =
        KeyboardTuning(
            a4Frequency = A4ConcertFrequency,
        )

    fun Guitar6String(
        firstString: Pitch,
        secondString: Pitch,
        thirdString: Pitch,
        fourthString: Pitch,
        fifthString: Pitch,
        sixthString: Pitch,
    ): FretboardTuning =
        listOf(firstString, secondString, thirdString, fourthString, fifthString, sixthString)
            .asFretboardTuning()

    object Guitar {

        val StandardTuning by lazy {
            Guitar6String(329.63f.hz, 246.94f.hz, 196.hz, 146.83f.hz, 110.hz, 82.41f.hz)
        }
    }

    val A4ConcertFrequency by lazy { 440.toBigDecimal() }
    val A4ConcertPitch by lazy { A4ConcertFrequency.toFloat().hz }
}