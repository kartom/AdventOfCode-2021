import aoc_utils.readStringListFile
import kotlin.math.sign

class Co(val x: Int, val y: Int) {
    override fun toString(): String {
        return "($x,$y)"
    }
}

class Line(str: String) {
    private val coList = str.replace(" -> ",",").split(',').map { it.toInt() }
    private val c = listOf(Co(coList[0],coList[1]),Co(coList[2],coList[3]))

    fun isNotDiagonal(): Boolean {
        return (c[0].x==c[1].x) or (c[0].y==c[1].y)
    }

    fun getLineList(): MutableList<Co> {
        val co = mutableListOf<Co>()
        val dx = sign((c[1].x-c[0].x).toFloat()).toInt()
        val dy = sign((c[1].y-c[0].y).toFloat()).toInt()
        var x  = c[0].x
        var y  = c[0].y
        co.add(Co(x,y))
        while( (x!=c[1].x) or (y!=c[1].y)) {
            x+=dx
            y+=dy
            co.add(Co(x,y))
        }
        return co
    }

    override fun toString():String {
        return "line$c"
    }

}

fun day5(lines: MutableList<Line>, diagonals: Boolean = false) {
    if( !diagonals )
        print("Day5 part 1: ")
    else
        print("Day5 part 2: ")
    val diagram = mutableMapOf<String,Int>()
    for( line in lines) {
        if (diagonals or line.isNotDiagonal())
            for (co in line.getLineList()) {
                diagram[co.toString()] = diagram.getOrDefault(co.toString(), 0)+1
            }
    }
    var count = 0
    for( point in diagram )
        if( point.value > 1 ) count+=1
    println(count)
}

fun main()  {
    val txtRows = readStringListFile("data/Day-05-data.txt")
    val lines = mutableListOf<Line>()
    for(txt in txtRows) {
        lines.add(Line(txt))
    }
    day5(lines)
    day5(lines, true)
}
