package io.github.mmolosay.musicmind.theory.distribution

///**
// * Represents a way to divide some range in a number of positions.
// * This range can be an octave, some scale or whole instrument range.
// */
//@JvmInline
//value class Distribution(val positions: Array<Position>) {
//
//    /**
//     * Position, defined by [Distribution], with [cents] from first zero-cent position.
//     */
//    @JvmInline
//    value class Position(val cents: Cents)
//}
//
///**
// * Each [distances] element defines how distant next position from the adjacent previous one.
// * This way, first element describes distance between first position and the second one, and so on.
// */
//internal fun Distribution(
//    distances: Array<Cents>,
//): Distribution {
//    var cents = Cents.Zero
//    val positions = Array(distances.size + 1) { i ->
//        if (i == 0) Distribution.Position(Cents.Zero)
//        else {
//            cents += distances[i - 1]
//            Distribution.Position(cents)
//        }
//    }
//    return Distribution(positions)
//}