import java.io.File

class Co(val x: Int, val y: Int) {
    override fun toString(): String = "($x,$y)"
}

class Line(str: String) {
    private val coList = str.replace(" -> ",",").split(',').map { it.toInt() }
    private val c = listOf(Co(coList[0],coList[1]),Co(coList[2],coList[3]))

    fun isNotDiagonal(): Boolean = (c[0].x==c[1].x) or (c[0].y==c[1].y)

    fun getLineList(): MutableList<Co> {
        val res = mutableListOf<Co>()
        val dx = if(c[1].x>c[0].x) 1 else (if(c[0].x>c[1].x) -1 else 0)
        val dy = if(c[1].y>c[0].y) 1 else (if(c[0].y>c[1].y) -1 else 0)
        var x  = c[0].x
        var y  = c[0].y
        res.add(Co(x,y))
        while( (x!=c[1].x) or (y!=c[1].y)) {
            x+=dx
            y+=dy
            res.add(Co(x,y))
        }
        return res
    }
}

fun countOverlap(lines: MutableList<Line>, diagonals: Boolean = false):Int {
    val diagram = mutableMapOf<String,Int>()
    for( line in lines) {
        if (diagonals or line.isNotDiagonal())
            for (co in line.getLineList()) {
                diagram[co.toString()] = diagram.getOrDefault(co.toString(), 0)+1
            }
    }
    var count = 0
    diagram.forEach { (_, value) -> if( value > 1 ) count+=1 }
    return count
}

fun main()  {
    val lines = mutableListOf<Line>()
    File("data/Day-05-data.txt").readLines().forEach { lines.add(Line(it))  }
    println("Day5 part 1: ${countOverlap(lines, false)}")
    println("Day5 part 2: ${countOverlap(lines, true)}")
}
