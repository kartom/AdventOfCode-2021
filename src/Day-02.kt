import aoc_utils.readStringIntFile

fun day2Part1() {
    val commands = readStringIntFile("data/Day-02-data.txt")
    var distance = 0
    var depth =0
    commands.forEach { command->
        when (command.first) {
            "forward" -> {
                distance += command.second
            }
            "up" -> {
                depth -= command.second
            }
            "down" -> {
                depth += command.second
            }
        }
    }
    println("Day2 - part 1: ${distance*depth}")
}

fun day2Part2() {
    val commands = readStringIntFile("data/Day-02-data.txt")
    var distance = 0
    var aim = 0
    var depth =0
    commands.forEach { command ->
        when (command.first) {
            "forward" -> {
                distance += command.second
                depth += aim*command.second
            }
            "up" -> {
                aim -= command.second
            }
            "down" -> {
                aim += command.second
            }
        }
    }
    println("Day2 - part 1: ${distance*depth}")
}

fun main()  {
    day2Part1()
    day2Part2()
}