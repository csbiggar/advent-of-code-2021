package days

import helpers.FileReader


fun main() {
    val part1result = Day5().countPointsWithOverlappingHorizontalOrVerticalLines(FileReader("/day5.txt").readText())
    println("Part 1: $part1result") //7297
}

data class Line(
    val start: Coordinate,
    val end: Coordinate
)

class Day5 {

    fun horizontalOrVerticalOnly(input: String): List<Line> {
        val lines = input.lines().map { line -> line.split(",", "->").map { it.trim().toInt() } }

        return lines
            .filter { it[0] == it[2] || it[1] == it[3] }
            .map {
                Line(
                    Coordinate(it[0], it[1]),
                    Coordinate(it[2], it[3])
                )
            }
    }

    fun pointsCoveredByLine(line: Line): List<Coordinate> {
        return when {
            line.start.y == line.end.y -> coordinatesCoveredByHorizontalLine(line)
            line.start.x == line.end.x -> coordinatesCoveredByVerticalLine(line)
            else -> throw IllegalStateException("not a horizontal or vertical line: $line")
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

    fun findNumberOfLinesPerCoordinate(lines: List<Line>): Map<Coordinate, Int> {
        return lines
            .flatMap { pointsCoveredByLine(it) }
            .groupBy { it }
            .mapValues { it.value.size }
    }

    fun countPointsWithOverlappingHorizontalOrVerticalLines(input: String): Int {
        val lines = horizontalOrVerticalOnly(input)
        return findNumberOfLinesPerCoordinate(lines).filter { it.value > 1 }.size
    }
}