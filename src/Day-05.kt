import kotlin.math.*

class Line(str: String) {
    private val c = str.replace(" -> ",",").split(',').map { it.toInt() }

    fun isNotDiagonal(): Boolean = (c[0]==c[2]) or (c[1]==c[3])

    fun getLineList(): List<Pair<Int,Int>> {
        val n = max(abs(c[2] - c[0]), abs(c[1]- c[3]))
        return (0 ..n).map { i-> Pair(c[0]+(c[2] - c[0])*i/n,c[1]+(c[3] - c[1])*i/n) }
    }
}

fun countOverlap(lines: List<Line>, diagonals: Boolean = false):Int {
    val diagram = mutableMapOf<String,Int>()
    lines.forEach { line ->
        if (diagonals or line.isNotDiagonal()) {
            line.getLineList().forEach { (x, y) ->
                diagram["$x,$y"] = diagram.getOrDefault("$x,$y", 0) + 1 }
        }
    }
    return diagram.count { it.value > 1  }
}

fun main()  {
    val lines = java.io.File("data/Day-05-data.txt").readLines().map { Line(it)  }
    println("Day5 part 1: ${countOverlap(lines, false)}")
    println("Day5 part 2: ${countOverlap(lines, true)}")
}
