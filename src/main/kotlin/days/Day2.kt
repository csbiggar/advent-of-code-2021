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
        val courses: Map<Direction, Int> = course
            .map {
                valueOf(it.substringBefore(DELIMITER)) to it.substringAfter(DELIMITER).toInt()
            }.groupBy { (command, _) -> command }
            .mapValues { (_, listOfPairs) -> listOfPairs.sumOf { it.second } }

        val depth = courses.getOrDefault(down, 0) - courses.getOrDefault(up, 0)
        val horizontal = courses.getOrDefault(forward, 0)

        return depth * horizontal
    }

    fun withAim(instructions: List<String>): Int {
        var aim = 0
        val steps = instructions
            .map {
                val instruction = Instruction(valueOf(it.substringBefore(DELIMITER)), it.substringAfter(DELIMITER).toInt())
                aim += calculateAimAdjustment(instruction)
                calculateStep(instruction, aim)
            }

        val horizontal = steps.sumOf { it.horizontal }
        val vertical = steps.sumOf { it.vertical }

        return horizontal * vertical
    }

    private fun calculateAimAdjustment(instruction: Instruction): Int {
        return when (instruction.direction) {
            down ->  instruction.value
            up -> instruction.value.unaryMinus()
            else -> 0
        }
    }

    private fun calculateStep(instruction: Instruction, aim: Int): Step {
        val horizontalStep = when (instruction.direction) {
            forward -> instruction.value
            else -> 0
        }

        val verticalStep = when (instruction.direction) {
            forward -> aim * instruction.value
            else -> 0
        }
        return Step(horizontalStep, verticalStep)
    }


}

enum class Direction {
    forward, up, down
}

data class Instruction(
    val direction: Direction,
    val value: Int
)

data class Step(
    val horizontal: Int,
    val vertical: Int
)

