package io.github.mmolosay.musicmind.theory.partition

val OctavePartition.isPentatonic: Boolean
    get() = (pieces == 5)

val OctavePartition.isHeptatonic: Boolean
    get() = (pieces == 7)

val OctavePartition.isDiatonic: Boolean
    get() = (isHeptatonic && TODO())
