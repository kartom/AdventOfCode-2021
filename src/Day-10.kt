import java.io.File

val match= mapOf('{' to '}','[' to ']', '(' to ')', '<' to '>')
val errorPoints = mapOf(')' to 3,']' to 57, '}' to 1197, '>' to 25137)
val autoCompletePoints = mapOf(')' to 1,']' to 2, '}' to 3, '>' to 4)

fun main()  {
    val lines = File("data/Day-10-data.txt").readLines()
    var errorScore = 0
    val autoCompleteScores = mutableListOf<Long>()
    lines.forEach { line ->
        var error= false
        val stack= mutableListOf<Char>()
        for( chr in line.toCharArray()) {
            if( match.containsKey(chr) ) {
                stack.add(match[chr]!!)
            }
            else {
                if( stack.last()==chr ) {
                    stack.removeLast()
                }
                else {
                    errorScore += errorPoints[chr]!!
                    error = true
                    break
                }
            }
        }
        if( !error ) {
            var score:Long = 0
            stack.reversed().forEach { chr -> score = score*5+autoCompletePoints[chr]!!  }
            autoCompleteScores.add(score)
        }
    }
    autoCompleteScores.sort()

    println("Day 10, part 1: $errorScore")
    println("Day 10, part 2: ${autoCompleteScores[autoCompleteScores.size/2]}")
}
