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
    fun `should find all points covered by a reverse horizontal line`() {

        val result = Day5().pointsCoveredByLine(
            Line(
                start = Coordinate(5, 4),
                end = Coordinate(2, 4)
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

    @Test
    fun `should find all points covered by a reverse vertical line`() {

        val result = Day5().pointsCoveredByLine(
            Line(
                start = Coordinate(4, 5),
                end = Coordinate(4, 2)
            )
        )

        assertThat(result).containsExactly(
            Coordinate(4, 2),
            Coordinate(4, 3),
            Coordinate(4, 4),
            Coordinate(4, 5)
        )
    }

    @Test
    fun `should find all points covered by a diagonal line top left to bottom right`() {

        val result = Day5().pointsCoveredByLine(
            Line(
                start = Coordinate(1, 1),
                end = Coordinate(3, 3)
            )
        )

        assertThat(result).containsExactly(
            Coordinate(1, 1),
            Coordinate(2, 2),
            Coordinate(3, 3),
        )
    }

    @Test
    fun `should find all points covered by a diagonal line top right to bottom left`() {

        val result = Day5().pointsCoveredByLine(
            Line(
                start = Coordinate(9, 7),
                end = Coordinate(7, 9)
            )
        )

        assertThat(result).containsExactlyInAnyOrder(
            Coordinate(9, 7),
            Coordinate(8, 8),
            Coordinate(7, 9),
        )
    }

    @Test
    fun `should find all points covered by diagonal line bottom right to top left`() {

        val result = Day5().pointsCoveredByLine(
            Line(
                start = Coordinate(6, 4),
                end = Coordinate(2, 0)
            )
        )

        assertThat(result).containsExactlyInAnyOrder(
            Coordinate(6, 4),
            Coordinate(5, 3),
            Coordinate(4, 2),
            Coordinate(3, 1),
            Coordinate(2, 0),
        )
    }

    @Test
    fun `should find all points covered by diagonal line bottom left to top right`() {

        val result = Day5().pointsCoveredByLine(
            Line(
                start = Coordinate(5, 5),
                end = Coordinate(8, 2)
            )
        )

        assertThat(result).containsExactlyInAnyOrder(
            Coordinate(5, 5),
            Coordinate(6, 4),
            Coordinate(7, 3),
            Coordinate(8, 2),
        )
    }

    @Test
    fun `should list coordinates with the number of lines passing through them`() {
        val line1 = Line(Coordinate(1, 1), Coordinate(5, 1))
        val line2 = Line(Coordinate(3, 1), Coordinate(3, 3))

        val result = Day5().findNumberOfLinesPerCoordinate(listOf(line1, line2))

        assertThat(result).containsEntry(Coordinate(3, 1), 2)
        assertThat(result).containsEntry(Coordinate(1, 1), 1)
        assertThat(result).containsEntry(Coordinate(3, 3), 1)
    }

    @Test
    fun `should list points belonging to more than one horizontal or vertical line in the given test case`() {
        val lines = Day5().horizontalOrVerticalOnly(testInput)
        val coordCount = Day5().findNumberOfLinesPerCoordinate(lines)

        assertThat(coordCount).containsAllEntriesOf(
            mapOf(
                Coordinate(0, 9) to 2,
                Coordinate(3, 4) to 2,
            )
        )

    }

    @Test
    fun `should list points belonging to more than one line in the given test case`() {
        val lines = Day5().asLines(testInput)
        val coordCount = Day5().findNumberOfLinesPerCoordinate(lines)

        assertThat(coordCount).containsAllEntriesOf(
            mapOf(
                Coordinate(7, 1) to 2,
                Coordinate(5, 3) to 2,
                Coordinate(4, 4) to 3,
            )
        )

    }

    @Test
    fun `should count how many coords have two or more horizontal or vertical  converging`() {
        val result = Day5().countPointsWithOverlappingHorizontalOrVerticalLines(testInput)

        assertThat(result).isEqualTo(5)
    }

    @Test
    fun `should count how many coords have two or more lines converging`() {
        val result = Day5().countPointsWithOverlappingLines(testInput)
        assertThat(result).isEqualTo(12)
    }
}


private const val testInput = """0, 9 -> 5, 9
        8, 0 -> 0, 8
        9, 4 -> 3, 4
        2, 2 -> 2, 1
        7, 0 -> 7, 4
        6, 4 -> 2, 0
        0, 9 -> 2, 9
        3, 4 -> 1, 4
        0, 0 -> 8, 8
        5, 5 -> 8, 2"""