package days

import helpers.FileReader
import java.lang.IllegalStateException

private const val DELIMITER = " "


fun main() {
    val input = FileReader("/day3.txt").readStrings()

    val resultPart1 = Day3().calculatePowerConsumption(input)
    println("Part 1: $resultPart1")

    val resultPart2 = Day3().calculateLifeSupportRating(input)
    println("Part 2: $resultPart2")
}


class Day3 {

    // Part 1
    fun calculatePowerConsumption(input: List<String>): Long {
        val gamma = findGammaRate(input)
        val epsilon = invertBinary(gamma)

        return gamma.toLong(2) * epsilon.toLong(2)
    }

    fun findGammaRate(input: List<String>): String {
        val finalIndex = input.first().count() - 1

        val binary = (0..finalIndex)
            .map { bitIndex ->
                input.getMostCommonAtIndex(bitIndex)
            }
            .joinToString("")

        return binary
    }

    private fun invertBinary(binary: String): String {
        return binary.replace('0', 'x')
            .replace('1', '0')
            .replace('x', '1')
    }

    // Part 2
    fun calculateLifeSupportRating(input: List<String>): Long {
        val o2Generator = findO2GeneratorRating(input)
        val co2Scrubber = findCO2ScrubberRating(input)
        return o2Generator.toLong(2) * co2Scrubber.toLong(2)
    }

    fun findO2GeneratorRating(input: List<String>): String {
        return filterMostCommon(input)
    }

    fun findCO2ScrubberRating(input: List<String>): String {
        return filterLeastCommon(input)
    }

    // Shared - refactor to combine most / least common
    private tailrec fun filterMostCommon(input: List<String>, bitIndex: Int = 0): String {
        val finalIndex = input.first().count() - 1
        if (bitIndex > finalIndex) throw IllegalStateException("Arg, too much loopiness")

        val mostCommonBitAtIndex = input.getMostCommonAtIndex(bitIndex)
        val filtered = input.filter { it[bitIndex] == mostCommonBitAtIndex }

        return when (filtered.size) {
            0 -> throw IllegalStateException("This can't be right, everything has been filtered out")
            1 -> filtered.first()
            else -> filterMostCommon(filtered, bitIndex + 1)
        }
    }

    private tailrec fun filterLeastCommon(input: List<String>, bitIndex: Int = 0): String {
        val finalIndex = input.first().count() - 1
        if (bitIndex > finalIndex) throw IllegalStateException("Arg, too much loopiness")

        val leastCommonBitAtIndex = input.getLeastCommonAtIndex(bitIndex)
        val filtered = input.filter { it[bitIndex] == leastCommonBitAtIndex }

        return when (filtered.size) {
            0 -> throw IllegalStateException("This can't be right, everything has been filtered out")
            1 -> filtered.first()
            else -> filterLeastCommon(filtered, bitIndex + 1)
        }
    }

    private fun List<String>.getMostCommonAtIndex(bitIndex: Int): Char {
        val bitValuesAtThisIndex = map { binaryNumber -> binaryNumber[bitIndex] }
        return mostCommon(bitValuesAtThisIndex)
    }

    private fun List<String>.getLeastCommonAtIndex(bitIndex: Int): Char {
        val bitValuesAtThisIndex = map { binaryNumber -> binaryNumber[bitIndex] }
        return leastCommon(bitValuesAtThisIndex)
    }


    // Move this out
    fun mostCommon(bits: List<Char>): Char {
        val (zeros, ones) = bitCounts(bits)
        return when {
            zeros > ones -> '0'
            else -> '1'
        }

    }

    fun leastCommon(bits: List<Char>): Char {
        val (zeros, ones) = bitCounts(bits)
        return when {
            zeros <= ones -> '0'
            else -> '1'
        }

    }

    private fun bitCounts(bits: List<Char>): BitCounts {
        val bitValueCount: Map<Char, Int> = bits
            .groupBy { it }
            .mapValues { (bitValue, bits) -> bits.size }

        val zeros = bitValueCount.getOrDefault('0', 0)
        val ones = bitValueCount.getOrDefault('1', 0)
        return BitCounts(zeros = zeros, ones = ones)
    }
}

data class BitCounts(
    val zeros: Int,
    val ones: Int
)