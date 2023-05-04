package io.github.mmolosay.musicmind.theory.tuning

object TuningSystems {

    /**
     * [12-TET – Wikipedia](https://en.wikipedia.org/wiki/12_equal_temperament)
     */
    fun EqualTemperament12Tone(): TuningSystem.EqualTemperament =
        TuningSystem.EqualTemperament(keysPerOctave = 12)
}