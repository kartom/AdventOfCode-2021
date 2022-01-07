fun calcDistance(commands: List<Pair<String, Int>>) = commands.sumOf { if( it.first == "forward") it.second else 0 }

fun calcDepth1(commands: List<Pair<String,Int>>) =
    commands.sumOf { (command, value) -> if( command == "up") -value else if (command == "down") value else 0 }

fun calcDepth2(commands: List<Pair<String,Int>>) =
    commands.indices.sumOf { i ->
        if( commands[i].first=="forward")
            commands.take(i).sumOf { (command, value)->
                if (command=="up") -value else if (command=="down") value else 0 }*commands[i].second
        else 0
    }

fun main()  {
    val commands =
    java.io.File("data/Day-02-data.txt").readLines().map { line ->
        line.split(" ").zipWithNext().map { Pair(it.first, it.second.toInt()) }.first()
    }

    println("Day 2, part 1: Depth*position=${calcDepth1(commands)*calcDistance(commands)}")
    println("Day 2, part 2: Depth*position=${calcDepth2(commands)*calcDistance(commands)}")
}