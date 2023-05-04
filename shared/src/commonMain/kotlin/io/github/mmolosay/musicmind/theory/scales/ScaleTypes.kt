package io.github.mmolosay.musicmind.theory.scales

val AtonalScale.isHeptatonic: Boolean
    get() = (size == 7)

val AtonalScale.isDiatonic: Boolean
    get() = (isHeptatonic && TODO())

val AtonalScale.isPentatonic: Boolean
    get() = (size == 5)