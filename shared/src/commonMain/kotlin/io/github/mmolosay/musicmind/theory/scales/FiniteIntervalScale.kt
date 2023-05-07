package io.github.mmolosay.musicmind.theory.scales

import io.github.mmolosay.musicmind.theory.intervals.KeyInterval

class FiniteIntervalScale internal constructor(
    intervals: List<KeyInterval>,
) : FiniteScale<KeyInterval>(intervals)