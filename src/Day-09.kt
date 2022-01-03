import java.io.File

fun basinSize(map: MutableList<String>, y: Int, x:Int): Int {
    if( map[y][x]=='9' ) return 0
    var size=1
    map[y]=StringBuilder(map[y]).also { it.setCharAt(x, '9') }.toString()
    if( x-1>=0 ) size+=basinSize(map,y,x-1)
    if( x+1<map[y].length) if( map[y][x+1] <= map[y][x]) size+=basinSize(map,y,x+1)
    if( y-1>=0 ) if( map[y-1][x] <= map[y][x]) size+=basinSize(map,y-1,x)
    if( y+1<map.size) if ( map[y+1][x] <= map[y][x]) size+=basinSize(map,y+1,x)
    return size
}

fun main() {
    val map = File("data/Day-09-data.txt").readLines()
    var sum=0
    val maxSizes= mutableListOf<Int>()
    for( y in map.indices) {
        for( x in map[y].indices) {
            var lowPoint = true
            if( x-1>=0 ) if( map[y][x-1] <= map[y][x]) lowPoint = false
            if( x+1<map[y].length) if( map[y][x+1] <= map[y][x]) lowPoint = false
            if( y-1>=0 ) if( map[y-1][x] <= map[y][x]) lowPoint = false
            if( y+1<map.size) if ( map[y+1][x] <= map[y][x]) lowPoint=false
            if( lowPoint) {
                sum +=  map[y][x].code-'0'.code+1
                val size = basinSize(map.toMutableList(),y,x)
                maxSizes.add(size)
            }
        }
    }
    maxSizes.sortDescending()

    println("Day 9, part 1: $sum")
    println("Day 9, part 2: ${maxSizes[0]*maxSizes[1]*maxSizes[2]}")
}
