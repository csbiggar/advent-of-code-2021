package days


class Day5(private val input: String) {

    fun horizontalOrVerticalOnly(): List<List<Int>> {
        val lines = input.lines().map { line -> line.split(",", "->").map { it.trim().toInt() } }

        return lines.filter { it[0] == it[2] || it[1] == it[3]}
    }
}