package days

import helpers.FileReader

fun main() {
    val input = FileReader("/day6.txt").readText().split(",").map { it.toInt() }

    val part1 = Day6().iterateOverDays(input, 80).size
    println("Part 1 $part1") //352195
}

class Day6 {
    fun iterate(testInput: List<Int>): List<Int> {
        val transformedFish = testInput.map { incrementFishClock(it) }
        val newFish = testInput.filter { it == 0 }.map { 8 }
        return transformedFish + newFish

    }

    private fun incrementFishClock(it: Int): Int {
        return if (it == 0) 6
        else it - 1
    }

    tailrec fun iterateOverDays(initialStates: List<Int>, days: Int = 1): List<Int> {
        val newStates = iterate(initialStates)
        if (days == 1) return newStates
        return iterateOverDays(newStates, days - 1)
    }
}