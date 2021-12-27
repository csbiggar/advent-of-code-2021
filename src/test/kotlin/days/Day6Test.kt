package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day6Test {


    @Test
    fun `should count down each fish`() {

        val laternfishStates = listOf(1, 2)

        val result = Day6().iterate(laternfishStates)
        assertThat(result).containsExactly(0, 1)
    }

    @Test
    fun `should translate 0 to 6 and add a new entry of 8 at end`() {

        val laternfishStates = listOf(0, 1)

        val result = Day6().iterate(laternfishStates)
        assertThat(result).containsExactly(6, 0, 8)
    }

    @Test
    fun `should transform 7 to 6 and not add a new fish`() {

        val laternfishStates = listOf(7, 1)

        val result = Day6().iterate(laternfishStates)
        assertThat(result).containsExactly(6, 0)
    }

    @Test
    fun `should add new fish for each 0`() {

        val laternfishStates = listOf(0, 0, 0)

        val result = Day6().iterate(laternfishStates)
        assertThat(result).containsExactly(6, 6, 6, 8, 8, 8)
    }

    @Test
    fun `should transform over give number of days`() {
        val initialStates = listOf(1, 2)

        val result = Day6().iterateOverDays(initialStates, 3)
        assertThat(result).containsExactly(5, 6, 7, 8)
    }

    @Test
    fun `should transform test case over given days`() {
        val initialStates = listOf(3,4,3,1,2)

        val result18 = Day6().iterateOverDays(initialStates, 18)
        assertThat(result18).hasSize(26)

        val result80 = Day6().iterateOverDays(initialStates, 80)
        assertThat(result80).hasSize(5934)
    }
}