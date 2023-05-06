package io.github.mmolosay.musicmind.theory.partition

import io.github.mmolosay.musicmind.theory.modes.Mode
import io.github.mmolosay.musicmind.theory.tuning.system.EqualTemperament
import io.github.mmolosay.musicmind.theory.tuning.system.PureIntonation
import io.github.mmolosay.musicmind.theory.tuning.system.TuningSystem

/**
 * Octave partition encapsulates a strategy of dividing an octave in a number of parts.
 *
 * Specified [distribution] is a non-empty list of successive [Distance]s __with__ a "looping" distance at the end.
 * A "looping" distance is a distance that makes a full octave with other distances.
 * Sum of all distances must be equal to the number of pitch classes in this tuning system.
 *
 * Example of the octave partition and its distribution list for a major scale in a 12-TET:
 *
 * `{ 2, 2, 1, 2, 2, 2, 1 }`.
 */
@JvmInline
value class OctavePartition internal constructor(val distribution: List<Distance>) {
    init {
        require(distribution.isNotEmpty()) { "You can't create empty OctavePartition" }
        require(distribution.size != 1) { "You can\'t create OctavePartition with only \"looping\" distance" }
    }
}

fun TuningSystem.verify(partition: OctavePartition) {
    when (this) {
        is EqualTemperament -> require(partition.distanceSum == pitchClasses)
        is PureIntonation -> TODO()
    }
}

infix fun OctavePartition.over(mode: Mode): OctavePartition {
    // TODO: tweak function when Modes can calculate their partitions
    require(isHeptatonic) { "Only heptatonic partitions may be used in modal interchange" }
    if (mode == Mode.Ionian) return this // TODO: remove this with todo above; used for now because Ionian will do nothing
    return distribution.run {
        OctavePartition(subList(mode.degree, size) + subList(0, mode.degree))
    }
}


val OctavePartition.pieces: Int
    get() = distribution.size

val OctavePartition.distanceSum: Int
    get() = distribution.sumOf { it.steps }