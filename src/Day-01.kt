import aoc_utils.readIntListFile

fun day1part1() {
    val numbers = readIntListFile("data/Day-01-data.txt").toMutableList()
    var increasing = 0
    var lastNum: Int = numbers[0]
    numbers.removeFirst()
    for( num in numbers) {
        if(num>lastNum) {
            increasing+=1
        }
        lastNum = num
    }
    println("Part1: Number of increasing numbers: $increasing")
}

fun day1part2() {
    val numbers = readIntListFile("data/Day-01-data.txt").toMutableList()
    var increasing = 0
    val avg = mutableListOf<Int>()
    avg.add(numbers[0])
    numbers.removeFirst()
    avg.add(numbers[0])
    numbers.removeFirst()
    avg.add(numbers[0])
    numbers.removeFirst()
    var lastAvg= avg[0]+avg[1]+avg[2]
    for( num in numbers) {
        avg.removeFirst()
        avg.add(num)
        val newAvg = avg[0]+avg[1]+avg[2]
        if(newAvg>lastAvg) {
            increasing+=1
        }
        lastAvg = newAvg
    }
    println("Part2: Number of increasing numbers: $increasing")
}

fun main()  {
    day1part1()
    day1part2()
}