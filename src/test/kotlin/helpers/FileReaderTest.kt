package helpers

import org.junit.jupiter.api.Test

internal class FileReaderTest{
    @Test
    fun `should read a file`() {
        FileReader("/day1input.txt").readText()
    }
}