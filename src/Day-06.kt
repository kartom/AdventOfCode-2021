import java.io.File

fun main()  {
    val fishes=File("data/Day-06-data.txt").readText().split(',').map{ it.toInt() }
    val fishState = Array<Long>(9) { 0 }
    for(fish in fishes) {
        fishState[fish]=fishState.getOrElse(fish) { 0 }+1
    }

    println("Day 6:")
    for(day in 1.rangeTo(256)) {
        val fishState0=fishState[0]
        for(i in 1.rangeTo(fishState.lastIndex)) {
            fishState[i-1]=fishState[i]
        }
        fishState[6]+=fishState0
        fishState[8]=fishState0
        if( day in listOf(18,80,256))
            println("${day.toString().padStart(3,' ')}: " +
                    "${fishState.sum().toString().padStart(12,' ')} " +
                    "[${fishState.map{ it.toString().padStart(4,' ')}.joinToString()}]")
    }
}
