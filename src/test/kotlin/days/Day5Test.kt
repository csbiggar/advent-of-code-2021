package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day5Test {

    @Test
    fun `should include horizontal lines`() {
        val input = """1,1 -> 1,2
            |1,1 -> 2,2
            |2,2 -> 2,4""".trimMargin()

        val result = Day5(input).horizontalOrVerticalOnly()

        assertThat(result).containsExactly(
            listOf(1, 1, 1, 2),
            listOf(2, 2, 2, 4)
        )
    }

    @Test
    fun `should include vertical lines`() {
        val input = """1,1 -> 3,2
            |1,2 -> 2,2
            |3,2 -> 2,4""".trimMargin()

        val result = Day5(input).horizontalOrVerticalOnly()

        assertThat(result).containsExactly(
            listOf(1, 2, 2, 2),
        )
    }
}


private val testInput = """0, 9 -> 5, 9
        8, 0 -> 0, 8
        9, 4 -> 3, 4
        2, 2 -> 2, 1
        7, 0 -> 7, 4
        6, 4 -> 2, 0
        0, 9 -> 2, 9
        3, 4 -> 1, 4
        0, 0 -> 8, 8
        5, 5 -> 8, 2"""