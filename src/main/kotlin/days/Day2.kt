package days

import days.Direction.down
import days.Direction.forward
import days.Direction.up
import days.Direction.valueOf
import helpers.FileReader

private const val DELIMITER = " "


fun main() {
    val input = FileReader("/day2input.txt").readStrings()

    val resultPart1 = Day2().horizontalXDepth(input)
    println("Part 1: $resultPart1")

    val resultPart2 = Day2().withAim(input)
    println("Part 2: $resultPart2")
}


class Day2 {
    fun horizontalXDepth(course: List<String>): Int {
        val courses: Map<String, Int> = course.map {
            it.substringBefore(DELIMITER) to it.substringAfter(DELIMITER).toInt()
        }.groupBy { (command, _) -> command }.mapValues { (_, listOfPairs) -> listOfPairs.sumOf { it.second } }

        val depth = courses.getOrDefault("down", 0) - courses.getOrDefault("up", 0)
        val horizontal = courses.getOrDefault("forward", 0)

        return depth * horizontal
    }

    fun withAim(course: List<String>): Int {
        var aim = 0
        val steps = course
            .map {
                aim = calculateAim(it, aim)
                calculateDirection(it, aim)
            }


        val horizontal = steps.sumOf { it.horizontal }
        val vertical = steps.sumOf { it.vertical }

        return horizontal * vertical
    }

    private fun calculateAim(instruction: String, currentAim: Int): Int {
        val direction = valueOf(instruction.substringBefore(DELIMITER))
        val value = instruction.substringAfter(DELIMITER).toInt()

        return when (direction) {
            down -> currentAim + value
            up -> currentAim - value
            else -> currentAim
        }
    }

    private fun calculateDirection(instruction: String, aim: Int): Step {
        val direction = valueOf(instruction.substringBefore(DELIMITER))
        val value = instruction.substringAfter(DELIMITER).toInt()

        val horizontalStep = when (direction) {
            forward -> value
            else -> 0
        }

        val verticalStep = when (direction) {
            forward -> aim * value
            else -> 0
        }
        return Step(horizontalStep, verticalStep)
    }


}

enum class Direction {
    forward, up, down
}

data class Step(
    val horizontal: Int,
    val vertical: Int
)

