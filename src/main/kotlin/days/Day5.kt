package days

data class Line(
    val start: Coordinate,
    val end: Coordinate
)

class Day5 {

    fun horizontalOrVerticalOnly(input: String): List<Line> {
        val lines = input.lines().map { line -> line.split(",", "->").map { it.trim().toInt() } }

        return lines
            .filter { it[0] == it[2] || it[1] == it[3]}
            .map {
                Line(
                    Coordinate(it[0], it[1]),
                    Coordinate(it[2], it[3])
                )
            }
    }

    fun pointsCoveredByLine(line: Line): List<Coordinate> {
        return when {
            line.start.y == line.end.y -> (line.start.x .. line.end.x).map { Coordinate(it, line.start.y) }
            line.start.x == line.end.x -> (line.start.y .. line.end.y).map { Coordinate(line.start.x, it) }
            else -> throw IllegalStateException("not a horizontal or vertical line: $line")
        }
    }
}