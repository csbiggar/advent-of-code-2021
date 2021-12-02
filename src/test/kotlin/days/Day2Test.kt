package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day2Test {

    @Test
    fun `should multiple depth by horizontal, allowing for missing directions`() {
        val result = Day2().horizontalXDepth(input)
        assertThat(result).isEqualTo(150)
    }

    private val input = listOf(
        "forward 5",
        "down 5",
        "forward 8",
        "up 3",
        "down 8",
        "forward 2",
    )
}