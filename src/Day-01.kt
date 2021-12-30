import aoc_utils.readIntListFile

fun day1part1() {
    val nums = readIntListFile("data/Day-01-data.txt")
    var increasing = 0
    var lastNum: Int = nums[0]
    nums.removeFirst()
    for( num in nums) {
        if(num>lastNum) {
            increasing+=1
        }
        lastNum = num
    }
    println("Part1: Number of increasing numbers: $increasing")
}

fun day1part2() {
    val nums = readIntListFile("data/Day-01-data.txt")
    var increasing = 0
    val avg = mutableListOf<Int>()
    avg.add(nums[0])
    nums.removeFirst()
    avg.add(nums[0])
    nums.removeFirst()
    avg.add(nums[0])
    nums.removeFirst()
    var lastAvg= avg[0]+avg[1]+avg[2]
    for( num in nums) {
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