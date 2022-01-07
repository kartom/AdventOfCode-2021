fun countIncreasing(numbers: List<Int>,avg:Int) =
    (avg until numbers.size).count { pos ->
        (pos-avg until pos).sumOf{ numbers[it] } < (pos-avg+1 until pos+1).sumOf{ numbers[it] } }

fun main()  {
    val numbers = java.io.File("data/Day-01-data.txt").readLines().map { it.toInt() }
    println("Day 1, part 1: Number of increasing values: ${countIncreasing(numbers,1)}")
    println("Day 1, part 2: Number of increasing values with sliding window: ${countIncreasing(numbers,3)}")
}