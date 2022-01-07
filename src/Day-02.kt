import java.io.File

fun day2Part1(commands: MutableList<Pair<String,Int>>) {
    var distance = 0
    var depth =0
    commands.forEach { command->
        when (command.first) {
            "forward" -> distance += command.second
            "up" -> depth -= command.second
            "down" -> depth += command.second
        }
    }
    println("Day2 - part 1: ${distance*depth}")
}

fun day2Part2(commands: MutableList<Pair<String,Int>>) {
    var distance = 0
    var aim = 0
    var depth =0
    commands.forEach { command ->
        when (command.first) {
            "forward" -> {
                distance += command.second
                depth += aim*command.second
            }
            "up" -> aim -= command.second
            "down" -> aim += command.second
        }
    }
    println("Day2 - part 1: ${distance*depth}")
}

fun main()  {
    val commands = mutableListOf<Pair<String, Int>>()
    File("data/Day-02-data.txt").forEachLine { line ->
        val (string, value) = line.split(" ")
        commands.add(Pair(string, value.toInt()))
    }
    day2Part1(commands)
    day2Part2(commands)
}