package io.github.mmolosay.musicmind.theory.scales

val Scale.isHeptatonic: Boolean
    get() = (size == 7)

val Scale.isDiatonic: Boolean
    get() = (isHeptatonic && TODO())

val Scale.isPentatonic: Boolean
    get() = (size == 5)