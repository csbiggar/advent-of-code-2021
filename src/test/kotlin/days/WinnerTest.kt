package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WinnerTest {

    @Test
    fun `calculate score`() {
        val board = BingoBoard(
            mapOf(
                Coordinate(0, 0) to BingoBoardEntry(3),
                Coordinate(0, 1) to BingoBoardEntry(15),
                Coordinate(1, 0) to BingoBoardEntry(9, true),
                Coordinate(1, 1) to BingoBoardEntry(18,true),
            )
        )

        // Given
        val winner = Winner(9, board)

        // Then score is sum of unmarked numbers * last number called
        assertThat(winner.getScore()).isEqualTo(162)
    }
}