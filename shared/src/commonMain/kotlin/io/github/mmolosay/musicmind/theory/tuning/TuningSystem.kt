package io.github.mmolosay.musicmind.theory.tuning


/**
 * A __tuning system__ is the system used to define which tones, or pitches, to use when playing music.
 * In other words, it is the choice of number and spacing of frequency values used.
 *
 * A __musical temperament__ is a tuning system that slightly compromises the pure intervals
 * of "just intonation" to meet other requirements.
 *
 * [Tuning systems – Wikipedia](https://en.wikipedia.org/wiki/Musical_tuning#Tuning_systems)
 * [Musical temperament – Wikipedia](https://en.wikipedia.org/wiki/Musical_temperament)
 */
sealed interface TuningSystem {

    /**
     * [Equal temperament – Wikipedia](https://en.wikipedia.org/wiki/Equal_temperament)
     */
    class EqualTemperament(
        val stepsPerOctave: Int,
    ) : TuningSystem
}