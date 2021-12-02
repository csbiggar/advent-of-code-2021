package days

import helpers.FileReader
import helpers.second


fun main() {
    val input = FileReader("/day1input.txt").readInts()

    val resultPart1 = Day1().countIncreases(input)
    println("Part 1: $resultPart1")

    val resultPart2 = Day1().countSlidingWindowIncreases(input)
    println("Part 2: $resultPart2")
}



class Day1 {
    fun countIncreases(measurements: List<Int>): Int {
        return measurements
            .windowed(2)
            .filter { it.first() < it.second() }
            .size
    }

    fun countSlidingWindowIncreases(measurements: List<Int>): Int {
        return measurements.windowed(3)
            .map { it.sum() }
            .windowed(2)
            .filter { it.first() < it.second() }
            .size
    }
}