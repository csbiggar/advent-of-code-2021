package days

import helpers.FileReader
import java.lang.IllegalStateException
import kotlin.math.sqrt

fun main() {
    val winner = Day4(FileReader("/day4.txt").readText()).findWinningBoard()

    println("Last number called: ${winner.lastNumberCalled}, board numbers: ${winner.board.getNumbers()}")

    println("Score: ${winner.getScore()}")  // 72770
}

class Day4(input: String) {
    val calledNumbers: List<Int> = input.lines()
        .first()
        .split(",")
        .map { it.toInt() }

    val bingoBoards: List<BingoBoard> = input.split("\n\n")
        .drop(1)
        .map { BingoBoard.create(it) }

    tailrec fun findWinningBoard(calledNumberIndex: Int = 0, currentBoardsStatus: List<BingoBoard> = bingoBoards): Winner {

        val newBoardsStatus = currentBoardsStatus.map { it.markCalledNumber(calledNumbers[calledNumberIndex]) }

        val winners = newBoardsStatus
            .filter { it.isWinner() }

        if (winners.size == 1) return Winner(calledNumbers[calledNumberIndex], winners.first())
        if (winners.size > 1) throw Exception("Too many winners!")

        val newCalledNumberIndex = calledNumberIndex + 1
        if (newCalledNumberIndex >= calledNumbers.size) throw Exception("All numbers called but no winning board found")

        return findWinningBoard(newCalledNumberIndex, newBoardsStatus)
    }

}

data class Winner(
    val lastNumberCalled: Int,
    val board: BingoBoard
) {
    fun getScore(): Long {
        val sumOfUnmarkedNumbers = board.bingoBoardEntries.values
            .filter { !it.marked }
            .sumOf { it.value }

        println("Sum of unmarked numbers: $sumOfUnmarkedNumbers")

        return (sumOfUnmarkedNumbers * lastNumberCalled).toLong()
    }
}

data class Coordinate(
    val x: Int,
    val y: Int
)

data class BingoBoardEntry(
    val value: Int,
    val marked: Boolean = false
)

data class BingoBoard(val bingoBoardEntries: Map<Coordinate, BingoBoardEntry>) {

    private val boardWidth = sqrt(bingoBoardEntries.size.toDouble()).toInt()
    private val boardHeight = boardWidth

    fun findMatchingEntry(calledNumber: Int): Coordinate? {
        val matchingEntries: Map<Coordinate, BingoBoardEntry> = bingoBoardEntries.filterValues { it.value == calledNumber }

        return when (matchingEntries.size) {
            1 -> matchingEntries.keys.first()
            0 -> null
            else -> throw IllegalStateException("${matchingEntries.size} entries found for called number $calledNumber")
        }
    }

    fun markCalledNumber(calledNumber: Int): BingoBoard {
        val matchingEntry = findMatchingEntry(calledNumber) ?: return this

        val newEntries = bingoBoardEntries.toMutableMap()
        newEntries[matchingEntry] = BingoBoardEntry(calledNumber, true)
        return BingoBoard(newEntries)
    }

    fun isWinner(): Boolean {
        (0 until boardWidth).forEach { x ->
            val rowComplete = bingoBoardEntries.filterKeys { it.x == x }.values.all { it.marked }
            if (rowComplete) return true
        }

        (0 until boardHeight).forEach { y ->
            val rowComplete = bingoBoardEntries.filterKeys { it.y == y }.values.all { it.marked }
            if (rowComplete) return true
        }

        return false
    }

    fun getNumbers(): List<Int> = bingoBoardEntries.values.map { it.value }


    companion object {
        fun create(boardAsString: String): BingoBoard {

            val rowsOfNumbers: List<List<Int>> = boardAsString.lines().map { splitToNumbers(it) }

            val entries: Map<Coordinate, BingoBoardEntry> = rowsOfNumbers
                .mapIndexed { x, numbers ->
                    numbers.mapIndexed { y, number ->
                        Coordinate(x, y) to BingoBoardEntry(number)
                    }
                }
                .flatten()
                .toMap()

            return BingoBoard(entries)
        }
    }
}

fun splitToNumbers(row: String): List<Int> {
    return row.trim().split("\\s+".toRegex()).map { it.toInt() }
}
