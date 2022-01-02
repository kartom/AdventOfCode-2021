import aoc_utils.readCSVintFile
import kotlin.math.abs
import kotlin.math.max

fun fuelCalc(crabs: List<Int>, fuelFun: (Int)->Int) {
    var maxPos = 0
    crabs.forEach { maxPos=max(it, maxPos) }
    val fuel = Array<Int>(maxPos+1) { 0 }
    for(pos in fuel.indices) {
        crabs.forEach { crab-> fuel[pos] += fuelFun(abs(crab - pos)) }
    }

    var minPos = 0
    var minFuel = Int.MAX_VALUE
    for( pos in fuel.indices) {
        if( fuel[pos]< minFuel ) {
            minFuel = fuel[pos]
            minPos= pos
        }
    }

    println("  Mix pos=$minPos Min fuel=$minFuel")
}

fun main()  {
    val crabs= readCSVintFile("data/Day-07-data.txt")
    println("Day 7, part 2:")
    fuelCalc(crabs,fun(n:Int)=n)
    println("Day 7, part 2:")
    fuelCalc(crabs,fun(n:Int)=(n*(1+n)/2))
}

