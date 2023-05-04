package io.github.mmolosay.musicmind.theory.tuning

import io.github.mmolosay.musicmind.theory.intervals.Interval
import io.github.mmolosay.musicmind.theory.intervals.asIntervals


/**
 * A __tuning system__ is the system used to define which tones, or pitches, to use when playing music.
 * In other words, it is the choice of number and spacing of frequency values used.
 *
 * A __musical temperament__ is a tuning system that slightly compromises the pure intervals
 * of "just intonation" to meet other requirements.
 *
 * Definition of major scale interval-wise is different for each tuning system: changing distribution of notes affects
 * minimal step of system (i.e. semitone for 12-TET). That changes amount of steps that an octave is split in, and thus
 * intervals of major scale.
 *
 * [Tuning systems – Wikipedia](https://en.wikipedia.org/wiki/Musical_tuning#Tuning_systems)
 * [Musical temperament – Wikipedia](https://en.wikipedia.org/wiki/Musical_temperament)
 */
sealed interface TuningSystem {

    val MajorIntervals: List<Interval>

    /**
     * [Equal temperament – Wikipedia](https://en.wikipedia.org/wiki/Equal_temperament)
     */
    class EqualTemperament(
        val keysPerOctave: Int,
    ) : TuningSystem {

        override val MajorIntervals: List<Interval> =
            listOf(2, 2, 1, 2, 2, 2, 1).asIntervals()
    }
}