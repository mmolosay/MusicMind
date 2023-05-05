package io.github.mmolosay.musicmind.theory.tuning.system

import io.github.mmolosay.musicmind.theory.cents.Cents
import io.github.mmolosay.musicmind.theory.intervals.Interval


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

    val pitchClasses: Int

    val majorIntervals: List<Interval>

    val step: Step

    /**
     * A step is the smallest interval between two adjacent notes.
     *
     * It is a neutral term that doesn't imply any particular size or tuning system.
     * In 12-TET it is a semitone, while in other tuning systems it could be something else.
     */
    sealed interface Step {
        class Constant(val size: Cents) : Step
        class Variable(val producer: Producer) : Step

        fun interface Producer {
            fun nextNoteFrom(/*TODO: define a way of defining note*/): Cents
        }
    }
}