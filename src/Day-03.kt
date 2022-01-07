val pow2= listOf(1, 2, 4, 8, 16, 32, 64 , 128, 256, 512, 1024)

fun main()  {
    val data  = java.io.File("data/Day-03-data.txt").readLines()
    val bits = data[0].length

    //Part 1
    val nHalf:Int = data.size/2
    val gamma = (0 until bits).sumOf { i -> if (data.count { it[i]=='1' }>nHalf) pow2[bits-i-1] else 0 }
    val epsilon = gamma xor (pow2[bits]-1)
    println("Day 3, part 1: Gamma rate=$gamma, Epsilon rate=$epsilon, Power consumption=${gamma*epsilon}")

    //Part 2
    val o2data = data.toMutableList()
    for( pos in 0 until bits ) {
        val n = o2data.count { it[pos]=='1' }
        o2data.removeIf { it[pos]!=if (n>=(o2data.size+1)/2) '1' else '0' }
        if( o2data.size == 1) break
    }
    val o2: Int = Integer.parseInt(o2data.first(),2)
    val co2data = data.toMutableList()
    for( pos in 0 until bits ) {
        val n = co2data.count { it[pos]=='1' }
        co2data.removeIf { it[pos]!=if (n<(co2data.size+1)/2) '1' else '0' }
        if( co2data.size == 1) break
    }
    val co2: Int = Integer.parseInt(co2data.first(),2)
    println("Day 3, part 2: O2 generator=$o2, CO2 scrubber=$co2, Life support=${o2*co2}")
}
