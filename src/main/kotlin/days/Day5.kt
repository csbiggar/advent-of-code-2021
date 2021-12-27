package days

import helpers.FileReader


fun main() {
    val input = FileReader("/day5.txt").readText()
    val part1result = Day5().countPointsWithOverlappingHorizontalOrVerticalLines(input)
    println("Part 1: $part1result") //7297

    val part2Result = Day5().countPointsWithOverlappingLines(input)
    println("Part 2: $part2Result") //21038

}

data class Line(
    val start: Coordinate,
    val end: Coordinate
)

class Day5 {

    fun horizontalOrVerticalOnly(input: String): List<Line> {
        return asLines(input)
            .filter { it.start.x == it.end.x || it.start.y == it.end.y }

    }

    fun asLines(input: String) = input.lines()
        .map { line -> line.split(",", "->").map { it.trim().toInt() } }
        .map {
            Line(
                Coordinate(it[0], it[1]),
                Coordinate(it[2], it[3])
            )
        }

    fun pointsCoveredByLine(line: Line): List<Coordinate> {
        return when {
            line.start.y == line.end.y -> coordinatesCoveredByHorizontalLine(line)
            line.start.x == line.end.x -> coordinatesCoveredByVerticalLine(line)
            else -> coordinatesCoveredByDiagonalLine(line)
        }
    }

    private fun coordinatesCoveredByHorizontalLine(line: Line): List<Coordinate> {
        return if (line.start.x < line.end.x) {
            (line.start.x..line.end.x).map { Coordinate(it, line.start.y) }
        } else {
            (line.end.x..line.start.x).map { Coordinate(it, line.start.y) }
        }
    }

    private fun coordinatesCoveredByVerticalLine(line: Line): List<Coordinate> {
        return if (line.start.y < line.end.y) {
            (line.start.y..line.end.y).map { Coordinate(line.start.x, it) }
        } else {
            (line.end.y..line.start.y).map { Coordinate(line.start.x, it) }
        }
    }

    private fun coordinatesCoveredByDiagonalLine(line: Line): List<Coordinate> {
        return when {
            line.start.x < line.end.x && line.start.y > line.end.y -> {
                (line.start.x..line.end.x)
                    .mapIndexed { counter, x ->
                        Coordinate(x, line.start.y - counter)
                    }
            }
            line.start.x > line.end.x && line.start.y < line.end.y -> {
                (line.end.x..line.start.x).mapIndexed { counter, x ->
                    Coordinate(x, line.end.y - counter)
                }
            }
            line.start.x > line.end.x && line.start.y > line.end.y -> {
                (line.end.x..line.start.x).mapIndexed { counter, x ->
                    Coordinate(x, line.end.y + counter)
                }
            }
            line.start.x < line.end.x && line.start.y < line.end.y -> {
                (line.start.x..line.end.x).mapIndexed { counter, x ->
                    Coordinate(x, line.start.y + counter)
                }
            }
            else -> throw Exception("arg")
        }
    }


    fun findNumberOfLinesPerCoordinate(lines: List<Line>): Map<Coordinate, Int> {
        return lines
            .flatMap {
                pointsCoveredByLine(it)
            }
            .groupBy { it }
            .mapValues { it.value.size }
    }

    fun countPointsWithOverlappingHorizontalOrVerticalLines(input: String): Int {
        val lines = horizontalOrVerticalOnly(input)
        return findNumberOfLinesPerCoordinate(lines).filter { it.value > 1 }.size
    }

    fun countPointsWithOverlappingLines(input: String): Int {
        val lines = asLines(input)
        return findNumberOfLinesPerCoordinate(lines).filter { it.value > 1 }.size
    }
}