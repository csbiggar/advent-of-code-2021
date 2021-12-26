package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day5Test {

    @Test
    fun `should include horizontal lines`() {
        val input = """1,1 -> 1,2
            |1,1 -> 2,2
            |2,2 -> 2,4""".trimMargin()

        val result = Day5().horizontalOrVerticalOnly(input)

        assertThat(result).containsExactly(
            Line(Coordinate(1, 1), Coordinate(1, 2)),
            Line(Coordinate(2, 2), Coordinate(2, 4))
        )
    }

    @Test
    fun `should include vertical lines`() {
        val input = """1,1 -> 3,2
            |1,2 -> 2,2
            |3,2 -> 2,4""".trimMargin()

        val result = Day5().horizontalOrVerticalOnly(input)

        assertThat(result).containsExactly(
            Line(Coordinate(1, 2), Coordinate(2, 2))
        )
    }

    @Test
    fun `should find all points covered by a horizontal line`() {

        val result = Day5().pointsCoveredByLine(
            Line(
                start = Coordinate(2, 4),
                end = Coordinate(5, 4)
            )
        )

        assertThat(result).containsExactly(
            Coordinate(2, 4),
            Coordinate(3, 4),
            Coordinate(4, 4),
            Coordinate(5, 4)
        )

    }

    @Test
    fun `should find all points covered by a vertical line`() {

        val result = Day5().pointsCoveredByLine(
            Line(
                start = Coordinate(4, 2),
                end = Coordinate(4, 5)
            )
        )

        assertThat(result).containsExactly(
            Coordinate(4, 2),
            Coordinate(4, 3),
            Coordinate(4, 4),
            Coordinate(4, 5)
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