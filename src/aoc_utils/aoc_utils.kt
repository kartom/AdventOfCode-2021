package aoc_utils

import java.io.File

typealias StringInt = Pair<String, Int>

/**
 * Reads a fle with an integer on each line
 *
 * @return List of all integers
 */
fun readIntListFile(filename: String): List<Int> {
    return File(filename).readLines().map { it.toInt() }
}

/**
 * Reads a file with a string and an integer separated by a singel space on each line
 * @return List of pair with the string ant int
 */
fun readStringIntFile(filename: String): MutableList<StringInt> {
    val res = mutableListOf<StringInt>()
    File(filename).forEachLine { line ->
        val (string, value) = line.split(" ")
        res.add(StringInt(string, value.toInt()))
    }
    return res
}

/**
 * Reads a file with one comma separated line of integers
 * @return List of integers
 */
fun readCSVintFile(filename: String): List<Int> {
    return File(filename).readText().split(',').map{ it.toInt() }
}
