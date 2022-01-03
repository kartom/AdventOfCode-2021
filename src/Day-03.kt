import java.io.File
import kotlin.math.pow

fun day3Part1(data: List<String>) {
    val nX = MutableList(data[0].length) {0}
    val nHalf:Int = data.size/2
    for (str in data) {
        for(i in 0 until nX.size) if( str[i]=='1' )  nX[i]+=1
    }
    var gamma = 0
    for(n in nX) {
        gamma = gamma shl 1
        if (n>nHalf) gamma+=1
    }
    val epsilon = gamma xor ((2.0).pow(nX.size)-1).toInt()
    println("Day 3, part 1: Gamma rate=$gamma, Epsilon rate=$epsilon, Power consumption=${gamma*epsilon}")
}

fun day3Part2(data: List<String>) {
    //O2
    var pos = 0
    val o2data = mutableListOf<String>()
    o2data.addAll(data)
    while (o2data.size>1) {
        var n = 0
        for (str in o2data) if( str[pos]=='1')  n+=1
        val digit = if (n>=(o2data.size+1)/2) '1' else '0'
        o2data.removeIf { it[pos]!=digit }
        pos+=1
    }
    val o2: Int = Integer.parseInt(o2data[0],2)
    //CO2
    pos=0
    val co2data = mutableListOf<String>()
    co2data.addAll(data)
    while (co2data.size>1) {
        var n = 0
        for (str in co2data) if( str[pos]=='1')  n+=1
        val digit = if (n<(co2data.size+1)/2) '1' else '0'
        co2data.removeIf { it[pos]!=digit }
        pos+=1
    }
    val co2: Int = Integer.parseInt(co2data[0],2)
    println("Day 3, part 2: O2 generator=$o2, CO2 scrubber=$co2, Life support=${o2*co2}")
}

fun main()  {
    val data  = File("data/Day-03-data.txt").readLines()
    day3Part1(data)
    day3Part2(data)
}
