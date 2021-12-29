import aoc_utils.readStringIntFile

fun day2Part1() {
    val commands = readStringIntFile("data/Day-02-data.txt")
    var distance = 0
    var depth =0
    for( command in commands ) {
        if( command.first == "forward" ) {
            distance += command.second
        } else if ( command.first == "up") {
            depth -= command.second
        } else if ( command.first == "down") {
            depth += command.second
        }
    }
    println("Day2 - part 1: ${distance*depth}")
}

fun day2Part2() {
    val commands = readStringIntFile("data/Day-02-data.txt")
    var distance = 0
    var aim = 0
    var depth =0
    for( command in commands ) {
        if( command.first == "forward" ) {
            distance += command.second
            depth += aim*command.second
        } else if ( command.first == "up") {
            aim -= command.second
        } else if ( command.first == "down") {
            aim += command.second
        }
    }
    println("Day2 - part 1: ${distance*depth}")
}

fun main()  {
    day2Part1()
    day2Part2()
}