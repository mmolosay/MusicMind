package io.github.mmolosay.musicmind.theory.scales

import io.github.mmolosay.musicmind.theory.intervals.Interval
import io.github.mmolosay.musicmind.theory.pitch.Pitch

/**
 * An implementation of scale with a [tonic] note, represented by an interval from
 * the first note of instrument.
 */
class Scale(
    val tonic: Interval, // TODO: change: should not depend on instrument
    override val intervals: List<Interval>,
) : AtonalScale {

    sealed interface Note {
        class KeyNote(
            val ordinal: Int,
        ) : Note
        class PitchNote(
            val pitch: Pitch,
        ) : Note
    }
}