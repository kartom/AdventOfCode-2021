fun calcFuel(crabs: List<Int>, fuelFun: (Int)->Int):Int = (0..crabs.maxOf {it} ).map { pos ->
        crabs.sumOf {  crab-> fuelFun( kotlin.math.abs(crab - pos)) }}.minOf { it }

fun main()  {
    val crabs= java.io.File("data/Day-07-data.txt").readText().split(',').map{ it.toInt() }
    println("Day 7, part 1:${calcFuel(crabs,fun(n:Int)=n)}")
    println("Day 7, part 2:${calcFuel(crabs,fun(n:Int)=(n*(1+n)/2))}")
}