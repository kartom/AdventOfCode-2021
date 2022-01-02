import java.io.File
import kotlin.math.pow



fun day3Part1(data: List<String>) {
    println("----------------------------------\nPart 1:")
    val nX = MutableList<Int>(data[0].length) {0}
    val nHalf:Int = data.size/2
    for (str in data) {
        for(i in 0 until nX.size) {
            if( str[i]=='1' ) {
                nX[i]+=1
            }
        }
    }
    var gamma = 0
    for(n in nX) {
        gamma = gamma shl 1
        if (n>nHalf) {
            gamma+=1
        }
    }
    val epsilon = gamma xor ((2.0).pow(nX.size)-1).toInt()
    println("Gamma rate: $gamma")
    println("Epsilon rate: $epsilon")
    println("Power consumption: ${gamma*epsilon}")
}

fun day3Part2(data: List<String>) {
    println("----------------------------------\nPart 2:")

    var pos = 0
    val O2data = mutableListOf<String>()
    O2data.addAll(data)
    while (O2data.size>1) {
        var n = 0
        for (str in O2data) {
            if( str[pos]=='1')  n+=1
        }
        val digit = if (n>=(O2data.size+1)/2) '1' else '0'
        // println("$pos: $n / ${O2data.size}(${(O2data.size+1)/2}) => $digit")
        O2data.removeIf { it[pos]!=digit }
        pos+=1
    }
    val O2: Int = Integer.parseInt(O2data[0],2)
    println("O2 generator: $O2")

    pos=0
    val CO2data = mutableListOf<String>()
    CO2data.addAll(data)
    while (CO2data.size>1) {
        var n = 0
        for (str in CO2data) {
            if( str[pos]=='1')  n+=1
        }
        val digit = if (n<(CO2data.size+1)/2) '1' else '0'
        // println("$pos: $n / ${CO2data.size}(${(CO2data.size+1)/2}) => $digit")
        CO2data.removeIf { it[pos]!=digit }
        pos+=1
    }
    val CO2: Int = Integer.parseInt(CO2data[0],2)
    println("CO2 scrubber: $CO2")

    println("Life support: ${O2*CO2}")
}

fun main()  {
    val data  = File("data/Day-03-data.txt").readLines()
    day3Part1(data)
    day3Part2(data)
}
