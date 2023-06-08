package io.github.mmolosay.musicmind.theory.tuning.system

@Suppress("FunctionName")
object TuningSystems {

    /**
     * [12-TET – Wikipedia](https://en.wikipedia.org/wiki/12_equal_temperament)
     */
    fun Tet12(): EqualTemperament =
        EqualTemperament(
            pitchClasses = 12,
        )
}