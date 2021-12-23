package days

import helpers.FileReader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day4Test {

    @Test
    fun `should store called numbers`() {
        val day4 = Day4(babyInput)
        assertThat(day4.calledNumbers).containsExactly(7, 4, 9)
    }

    @Test
    fun `should store boards`() {
        val expectedCard1 = BingoBoard(
            mapOf(
                Coordinate(0, 0) to BingoBoardEntry(22),
                Coordinate(0, 1) to BingoBoardEntry(13),
                Coordinate(1, 0) to BingoBoardEntry(8),
                Coordinate(1, 1) to BingoBoardEntry(2),
            )
        )

        val expectedCard2 = BingoBoard(
            mapOf(
                Coordinate(0, 0) to BingoBoardEntry(3),
                Coordinate(0, 1) to BingoBoardEntry(15),
                Coordinate(1, 0) to BingoBoardEntry(9),
                Coordinate(1, 1) to BingoBoardEntry(18),
            )
        )

        // Expect
        val day4 = Day4(babyInput)
        assertThat(day4.bingoBoards).containsExactly(expectedCard1, expectedCard2)
    }


    @Test
    fun `should not fall over with day 4 input`() {
        Day4(FileReader("/day4.txt").readText())
    }

    @Test
    fun `marked row wins`() {
        val board = BingoBoard(
            mapOf(
                Coordinate(0, 0) to BingoBoardEntry(3),
                Coordinate(0, 1) to BingoBoardEntry(15),
                Coordinate(1, 0) to BingoBoardEntry(9, true),
                Coordinate(1, 1) to BingoBoardEntry(18,true),
            )
        )

        val result = board.isWinner()
        assertThat(result).isTrue
    }

    @Test
    fun `marked column wins`() {
        val board = BingoBoard(
            mapOf(
                Coordinate(0, 0) to BingoBoardEntry(3, true),
                Coordinate(0, 1) to BingoBoardEntry(15),
                Coordinate(1, 0) to BingoBoardEntry(9, true),
                Coordinate(1, 1) to BingoBoardEntry(18),
            )
        )

        val result = board.isWinner()
        assertThat(result).isTrue
    }

    @Test
    fun `incomplete row or column does not win`() {
        val board = BingoBoard(
            mapOf(
                Coordinate(0, 0) to BingoBoardEntry(3, true),
                Coordinate(0, 1) to BingoBoardEntry(15),
                Coordinate(1, 0) to BingoBoardEntry(9),
                Coordinate(1, 1) to BingoBoardEntry(18),
            )
        )

        val result = board.isWinner()
        assertThat(result).isFalse()
    }


    @Test
    fun `should find winning bingo card with the last called number`() {
        val expectedWinner = BingoBoard.create("""14 21 17 24  4
10 16 15  9 19
18  8 23 26 20
22 11 13  6  5
 2  0 12  3  7""")

        val result = Day4(input).findWinningBoards().first()
        assertThat(result.board.getNumbers()).isEqualTo(expectedWinner.getNumbers())
        assertThat(result.lastNumberCalled).isEqualTo(24)
    }

    @Test
    fun `should find losing board`() {
        val expectedLoser = BingoBoard.create(""" 3 15  0  2 22
 9 18 13 17  5
19  8  7 25 23
20 11 10 24  4
14 21 16 12  6""")

        val result = Day4(input).findLastWinningBoard()
        assertThat(result.board.getNumbers()).isEqualTo(expectedLoser.getNumbers())
        assertThat(result.lastNumberCalled).isEqualTo(13)
    }

    private val babyInput = """7,4,9

22 13 
 8  2   

 3 15
 9 18""".trimIndent()


    private val input = """7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1

22 13 17 11  0
 8  2 23  4 24
21  9 14 16  7
 6 10  3 18  5
 1 12 20 15 19

 3 15  0  2 22
 9 18 13 17  5
19  8  7 25 23
20 11 10 24  4
14 21 16 12  6

14 21 17 24  4
10 16 15  9 19
18  8 23 26 20
22 11 13  6  5
 2  0 12  3  7""".trimIndent()

}