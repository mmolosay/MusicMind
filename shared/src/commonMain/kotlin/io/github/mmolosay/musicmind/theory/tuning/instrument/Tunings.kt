package io.github.mmolosay.musicmind.theory.tuning.instrument

import io.github.mmolosay.musicmind.theory.pitch.Pitch
import io.github.mmolosay.musicmind.theory.pitch.hz

@Suppress("FunctionName")
object Tunings {

    fun ConcertPiano(): KeyboardTuning =
        KeyboardTuning(
            a4Frequency = 440.toBigDecimal(), // concert pitch
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
            .asReversed() // we want first guitar's key to be an open sixth string, not the first one
            .asFretboardTuning()

    object Guitar {

        val StandardTuning by lazy {
            Guitar6String(329.63.hz, 246.94.hz, 196.hz, 146.83.hz, 110.hz, 82.41.hz)
        }
    }
}