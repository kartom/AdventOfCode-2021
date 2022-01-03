import java.io.File

val valueToChar = mapOf(241 to 'a', 198 to 'b', 200 to 'c', 212 to 'd', 146 to 'e', 236 to 'f', 232 to 'g')
val charToDigit = mapOf("abcefg" to 0, "cf" to 1, "acdeg" to 2, "acdfg" to 3, "bcdf" to 4, "abdfg" to 5,
                        "abdefg" to 6, "acf" to 7, "abcdefg" to 8, "abcdfg" to 9)

fun day8part1(lines: List<String>) {
    var cnt = 0
    lines.forEach { line ->
        val (_, output) = line.split('|')
        output.split(' ').forEach { digit ->
            if( digit.length in setOf(2,3,4,7)) cnt+=1
        }
    }
    println("Day 8, part 1: $cnt")
}

fun day8part2(lines: List<String>) {
    var sum:Long = 0
    lines.forEach { line ->
        val (patterns, output) = line.split('|')
        val m = mutableMapOf<Char,Int>()
        patterns.split(' ').forEach { pattern ->
            val value = pattern.length*pattern.length
            pattern.forEach { chr ->
                m[chr]=m.getOrDefault(chr,0)+value
            }
        }
        val translate = mutableMapOf<Char,Char>()
        m.forEach { (chr, value) ->
            val toChar:Char = valueToChar[value]!!
            translate[toChar] = chr
        }
        var number = 0
        output.trim().split(' ').forEach { rawDigit ->
            var correctDigit = ""
            "abcdefg".forEach { chr -> if( translate[chr]!! in rawDigit ) correctDigit+= chr }
            number=number*10+charToDigit[correctDigit]!!
        }
        sum+=number
    }
    println("Day 8, part 2: $sum")
}

fun main()  {
    val lines = File("data/Day-08-data.txt").readLines()
    day8part1(lines)
    day8part2(lines)
}
