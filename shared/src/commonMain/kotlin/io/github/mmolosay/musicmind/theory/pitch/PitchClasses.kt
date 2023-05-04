package io.github.mmolosay.musicmind.theory.pitch

object PitchClasses {

    fun List<Pitch>.classes(): List<PitchClass> {
        val list = mutableListOf<PitchClass>()
        val classes = countClasses()
        val end = first() * 2
        var ordinal = 0
        // do while we don't reach an octave
        while (get(ordinal) != end) {
            val pitches = mutableListOf<Pitch>()
            for (i in ordinal until size step classes) {
                pitches.add(get(i))
            }
            list.add(PitchClass(pitches))
            ordinal++
        }
        return list
    }

    private fun List<Pitch>.countClasses(): Int {
        if (isEmpty()) return 0
        if (size == 1) return 1
        val end = first() * 2
        var i = 1
        while (get(i) != end && i != lastIndex) i++
        return i
    }
}