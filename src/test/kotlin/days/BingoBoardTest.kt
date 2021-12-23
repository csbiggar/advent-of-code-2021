package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class BingoBoardTest {

    @Test
    fun `should find matching entry`() {
        val card = BingoBoard(
            mapOf(
                Coordinate(0, 0) to BingoBoardEntry(3),
                Coordinate(0, 1) to BingoBoardEntry(5),
                Coordinate(1, 0) to BingoBoardEntry(7),
                Coordinate(1, 1) to BingoBoardEntry(11),
            )
        )

        assertThat(card.findMatchingEntry(5)).isEqualTo(Coordinate(0, 1))
    }

    @Test
    fun `should return null for missing entry`() {
        val card = BingoBoard(
            mapOf(
                Coordinate(0, 0) to BingoBoardEntry(3),
                Coordinate(0, 1) to BingoBoardEntry(5),
                Coordinate(1, 0) to BingoBoardEntry(7),
                Coordinate(1, 1) to BingoBoardEntry(11),
            )
        )

        assertThat(card.findMatchingEntry(2)).isNull()
    }

    @Test
    fun `should fail if more than one matching entry found`() {
        val card = BingoBoard(
            mapOf(
                Coordinate(0, 0) to BingoBoardEntry(3),
                Coordinate(0, 1) to BingoBoardEntry(5),
                Coordinate(1, 0) to BingoBoardEntry(5),
                Coordinate(1, 1) to BingoBoardEntry(11),
            )
        )

        assertThrows<IllegalStateException> { (card.findMatchingEntry(5)) }
    }

    @Test
    fun `should create board from string`(){

        val boardAsString = """ 1  2  
            |33  44""".trimMargin()

            val expectedCard1 = BingoBoard(
                mapOf(
                    Coordinate(0, 0) to BingoBoardEntry(1),
                    Coordinate(0, 1) to BingoBoardEntry(2),
                    Coordinate(1, 0) to BingoBoardEntry(33),
                    Coordinate(1, 1) to BingoBoardEntry(44),
                )
            )

            // Expect
            assertThat(BingoBoard.create(boardAsString)).isEqualTo(expectedCard1)
    }

    @Test
    fun `should split a single row string into numbers`() {

        val result: List<Int> = splitToNumbers("  3  44  5 ")

        assertThat(result).containsExactly(3, 44, 5)

    }
    
    @Test
    fun `should update called number to marked`(){
        val initialCard = BingoBoard(
            mapOf(
                Coordinate(0, 0) to BingoBoardEntry(3),
                Coordinate(0, 1) to BingoBoardEntry(5),
                Coordinate(1, 0) to BingoBoardEntry(7),
                Coordinate(1, 1) to BingoBoardEntry(11),
            )
        )      
        
        val markedCard = BingoBoard(
            mapOf(
                Coordinate(0, 0) to BingoBoardEntry(3),
                Coordinate(0, 1) to BingoBoardEntry(5, true),
                Coordinate(1, 0) to BingoBoardEntry(7),
                Coordinate(1, 1) to BingoBoardEntry(11),
            )
        )

        assertThat(initialCard.markCalledNumber(5)).isEqualTo(markedCard)
    }

    @Test
    fun `should list numbers on board`() {

        val card = BingoBoard(
            mapOf(
                Coordinate(0, 0) to BingoBoardEntry(3),
                Coordinate(0, 1) to BingoBoardEntry(5),
                Coordinate(1, 0) to BingoBoardEntry(7),
                Coordinate(1, 1) to BingoBoardEntry(11),
            )
        )
        val numbers = card.getNumbers()
        assertThat(numbers).containsExactlyInAnyOrder(3,5,7,11)
    }

}