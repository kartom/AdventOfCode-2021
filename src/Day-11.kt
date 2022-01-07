import java.io.File

class OctopusGrid(filename: String, private val size:Int) {
    private val map = Array(size) { Array(size) { 0 } }
    private val rng = 0 until size
    init {
        var row =0
        File(filename).readLines().forEach { line ->
            for( col in line.indices ) { map[row][col]= line[col].code-'0'.code }
            row+=1
        }
    }

    private fun incAndFlash(row: Int, col: Int): Int {
        if(row !in rng) return 0
        if( col !in rng ) return 0
        map[row][col]+=1
        var flashes =0
        if( map[row][col]==10 ) {
            flashes=1
            for( dr in -1..1) for( dc in -1 .. 1) flashes+=incAndFlash(row+dr, col+dc)
        }
        return flashes
    }

    fun step():Int {
        var flashes =0
        for( row in rng) for( col in rng) flashes+=incAndFlash(row,col)
        for( row in rng) for( col in rng) if( map[row][col]>9 ) map[row][col]=0
        return flashes
    }

    fun allFlash(): Boolean = ( map.sumOf { line -> line.sumOf { it } } == 0 )
}

fun main()  {
    val grid=OctopusGrid("data/Day-11-data.txt",10)

    var flashes = 0
    for( j in 1..100) { flashes += grid.step() }
    println("Day 11, part 1: After 100 steps, there have been a total of $flashes flashes")

    var cnt=100
    while( !grid.allFlash()) {
        cnt+=1
        grid.step()
    }
    println("Day 11, part 2: All octopuses flash after step $cnt")
}