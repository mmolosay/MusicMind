package io.github.mmolosay.musicmind.theory.modes

/**
 * [Mode – Wikipedia](https://en.wikipedia.org/wiki/Mode_(music))
 */
enum class Mode(val degree: Int) {
    Ionian(degree = 1),
    Dorian(degree = 2),
    Phrygian(degree = 3),
    Lydian(degree = 4),
    Mixolydian(degree = 5),
    Aeolian(degree = 6),
    Locrian(degree = 7),
    ;
}