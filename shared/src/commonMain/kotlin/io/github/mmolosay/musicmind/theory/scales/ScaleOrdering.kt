package io.github.mmolosay.musicmind.theory.scales

val <T : Comparable<T>> FiniteScale<T>.isAscending: Boolean
    get() = if (entries.size > 1) (entries[0] >= entries[1]) else true

val <T : Comparable<T>> FiniteScale<T>.isDescending: Boolean
    get() = !isAscending