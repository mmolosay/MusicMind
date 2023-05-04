package io.github.mmolosay.musicmind.theory.pitch

object PitchClasses {

    fun List<Pitch>.classes(): List<PitchClass> {
        val count = countClasses()
        val classes = List(count) { mutableListOf<Pitch>() }
        for (i in indices) {
            classes[i % count].add(get(i))
        }
        return classes.map { PitchClass(it) }
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