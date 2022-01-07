import java.io.File
import kotlin.math.abs

fun calcFuel(crabs: List<Int>, fuelFun: (Int)->Int):Int {
    val fuel = Array(crabs.maxOf { it } +1) { 0 }
    for(pos in fuel.indices) {
        crabs.forEach { crab-> fuel[pos] += fuelFun(abs(crab - pos)) }
    }

    return fuel.minOf { it }
}

fun main()  {
    val crabs= File("data/Day-07-data.txt").readText().split(',').map{ it.toInt() }
    println("Day 7, part 2:${calcFuel(crabs,fun(n:Int)=n)}")
    println("Day 7, part 2:${calcFuel(crabs,fun(n:Int)=(n*(1+n)/2))}")
}

