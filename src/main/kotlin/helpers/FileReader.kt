@file:JvmName("FileReader")

package helpers

import days.Day1
import java.io.File

class FileReader(private val afileName: String) {
    private val file = File(Day1::class.java.getResource(afileName)!!.file)

    fun readStrings(): List<String> {
        return file.readLines()
    }

    fun readText(): String {
        return file.readText()
    }

    fun readInts(): List<Int> {
        val file = File(FileReader::class.java.getResource(afileName)!!.file)

        return file.readLines().map { it.toInt() }
    }
}