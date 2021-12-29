package aoc_utils

import java.io.File

typealias StringInt = Pair<String, Int>

fun readIntListFile(filename: String): MutableList<Int> {
    val res = mutableListOf<Int>()
    File(filename).forEachLine { str -> res.add(str.toInt())}
    return res
}

fun readStringListFile(filename: String): MutableList<String> {
    val res = mutableListOf<String>()
    File(filename).forEachLine { str -> res.add(str)}
    return res
}

fun readStringIntFile(filename: String): MutableList<StringInt> {
    val res = mutableListOf<StringInt>()
    File(filename).forEachLine { line ->
        val (string, value) = line.split(" ")
        res.add(StringInt(string, value.toInt()))
    }
    return res
}

