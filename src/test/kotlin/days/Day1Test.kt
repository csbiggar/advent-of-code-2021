package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day1Test {

    @Test
    fun `should count number of measurements which were increases on the previous`() {
        val result = Day1().countIncreases(input)
        assertThat(result).isEqualTo(7)
    }

    @Test
    fun `should count sliding window increases`() {
        val result = Day1().countSlidingWindowIncreases(input)
        assertThat(result).isEqualTo(5)
    }

    private val input = listOf(
        199,
        200,
        208,
        210,
        200,
        207,
        240,
        269,
        260,
        263,
    )
}