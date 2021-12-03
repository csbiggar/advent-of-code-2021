package days

import helpers.FileReader
import java.lang.IllegalStateException

private const val DELIMITER = " "


fun main() {
    val input = FileReader("/day3.txt").readStrings()

    val resultPart1 = Day3().getPowerConsumption(input)
    println("Part 1: $resultPart1")
//
//    val resultPart2 = Day3().withAim(input)
//    println("Part 2: $resultPart2")
}


class Day3 {
    fun getPowerConsumption(input: List<String>): Long {
        val gamma = findGammaRate(input)
        val epsilon = invertBinary(gamma)

        return gamma.toLong(2) * epsilon.toLong(2)
    }

    fun findGammaRate(input: List<String>): String {
        val finalIndex = input.first().count() - 1

        val binary = (0..finalIndex)
            .map { bitIndex ->
                val atThisIndex = input.map { binaryNumber -> binaryNumber[bitIndex] }
                mostCommon(atThisIndex)
            }
            .joinToString("")

        return binary
    }

    private fun invertBinary(binary: String): String {
        return binary.replace('0', 'x')
            .replace('1', '0')
            .replace('x', '1')
    }

    fun mostCommon(bits: List<Char>): Char {
        val bitValueCount: Map<Char, Int> = bits
            .groupBy { it }
            .mapValues { (bitValue, bits) -> bits.size }

        val zeros = bitValueCount.getOrDefault('0', 0)
        val ones = bitValueCount.getOrDefault('1', 0)

        return when {
            zeros > ones -> '0'
            ones > zeros -> '1'
            else -> throw IllegalStateException("Contains equal numbers of 0s and 1s")
        }

    }


}