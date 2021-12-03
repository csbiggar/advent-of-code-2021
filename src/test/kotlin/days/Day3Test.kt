package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalStateException

internal class Day3Test {

    @Test
    fun `part 1`() {
        val result = Day3().getPowerConsumption(input)
        assertThat(result).isEqualTo(198)
    }

    @Test
    fun `gamma rate should be compiled from most common bit in each position`() {
        val result = Day3().findGammaRate(input)
        assertThat(result).isEqualTo(22)
    }

    @Test
    fun `should find the most common digit in a list of 0s and 1s`() {
        val input1 = listOf('1', '0', '1')
        val input2 = listOf('1', '0', '0')
        val input3 = listOf('1', '0', '0', '1')

        assertThat(Day3().mostCommon(input1)).`as`("mostly ones").isEqualTo("1")
        assertThat(Day3().mostCommon(input2)).`as`("mostly zeros").isEqualTo("0")
        val exception = assertThrows<IllegalStateException> { Day3().mostCommon(input3) }
        assertThat(exception.message).`as`("same number").contains("Contains equal numbers of 0s and 1s")

    }

    private val input = listOf(
        "00100",
        "11110",
        "10110",
        "10111",
        "10101",
        "01111",
        "00111",
        "11100",
        "10000",
        "11001",
        "00010",
        "01010",
    )
}


