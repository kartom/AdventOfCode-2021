import java.io.File

fun countWays(routes: Map<String, MutableList<String>>, canVisitDouble:Boolean,
              from: String="start", visited: List<String> = listOf()):Int  {
    //println("Find way from $from")
    if( from == "end") return 1
    val thisVisited = visited.toMutableList()
    var paths=0
    if( from[0]>='a') thisVisited.add(from)
    routes[from]!!.forEach { cave ->
        if( cave !in visited )
            paths+=countWays(routes, canVisitDouble, cave, thisVisited)
        else if( canVisitDouble and (cave!="start"))
            paths+=countWays(routes,false, cave, thisVisited)
    }
    return paths
}

fun main() {
    val rules = File("data/Day-12-data.txt").readLines()
    val routes = mutableMapOf<String,MutableList<String>>()
    rules.forEach { rule ->
        val (cave1, cave2) = rule.split('-')

        if (!routes.containsKey(cave1)) routes[cave1] = mutableListOf()
        routes[cave1]!!.add(cave2)
        if (!routes.containsKey(cave2)) routes[cave2] = mutableListOf()
        routes[cave2]!!.add(cave1)
    }

    println("Day 12, part 1: The cave has ${countWays(routes, canVisitDouble = false)} paths through it")
    println("Day 12, part 2: The cave has ${countWays(routes, canVisitDouble = true)} paths through it " +
            "if you can visit one small cave twice")
}