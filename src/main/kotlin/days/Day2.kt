package days

import helpers.FileReader

private const val DELIMITER = " "


fun main() {
    val input = FileReader("/day2input.txt").readStrings()

    val resultPart1 = Day2().horizontalXDepth(input)
    println("Part 1: $resultPart1")

//    val resultPart2 = Day2().countSlidingWindowIncreases(input)
//    println("Part 2: $resultPart2")
}


class Day2 {
    fun horizontalXDepth(course: List<String>): Int {
        val courses: Map<String, Int> =  course
            .map {
                it.substringBefore(DELIMITER) to it.substringAfter(DELIMITER).toInt()
            }
            .groupBy { (command, _) -> command }
            .mapValues { (_, listOfPairs) -> listOfPairs.sumOf { it.second } }

        val depth = courses.getOrDefault("down", 0) - courses.getOrDefault("up", 0)
        val horizontal = courses.getOrDefault("forward", 0) - courses.getOrDefault("back", 0)

        return depth * horizontal
    }

}