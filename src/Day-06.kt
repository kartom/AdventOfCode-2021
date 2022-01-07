
fun main()  {
    val fishState = Array<Long>(9) { 0 }
    java.io.File("data/Day-06-data.txt").readText().split(',').map{ it.toInt() }.forEach { fish ->
        fishState[fish]=fishState[fish]+1 }

    println("Day 6:")
    for(day in 1.rangeTo(256)) {
        val fishState0=fishState[0]
        (1.rangeTo(fishState.lastIndex)).forEach { i-> fishState[i-1]=fishState[i] }
        fishState[6]+=fishState0
        fishState[8]=fishState0
        if( day in listOf(18,80,256))
            println("${day.toString().padStart(3,' ')}: " +
                    "${fishState.sum().toString().padStart(12,' ')} " +
                    "[${fishState.joinToString { it.toString().padStart(4, ' ') }}]")
    }
}
