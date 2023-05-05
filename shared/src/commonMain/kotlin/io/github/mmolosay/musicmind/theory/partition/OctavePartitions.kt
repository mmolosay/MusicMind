package io.github.mmolosay.musicmind.theory.partition

import io.github.mmolosay.musicmind.theory.tuning.system.TuningSystem

object OctavePartitions {

    fun TuningSystem.partition(
        distribution: List<Distance>, // can't use vararg :(
    ): OctavePartition =
        OctavePartition(distribution).also { verify(it) }

    fun TuningSystem.partition(
        vararg distribution: Int,
    ): OctavePartition =
        partition(distribution.toList().asDistances())
}