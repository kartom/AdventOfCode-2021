fun fold(dots: Set<Pair<Int,Int>>, axis: String, pos: Int): Set<Pair<Int,Int>> = dots.map { (x,y) ->
    if( (axis=="x") and (x>pos) ) Pair(pos*2-x,y) else
        if ( (axis=="y") and (y>pos) ) Pair(x,pos*2-y) else Pair(x,y)}.toSet()

fun main()  {
    val lines = java.io.File("data/Day-13-data.txt").readLines()
    val dots= lines.takeWhile { it.isNotBlank() }.map { line ->
        line.split(',').map{ it.toInt()}.zipWithNext().first()}.toSet()
    val folds= lines.takeLastWhile { it.isNotBlank() }.map{line ->
        line.split(' ')[2].split('=').zipWithNext().first()}

    println("Day 13, part 1: Dots after the first fold=${fold(dots,folds[0].first, folds[0].second.toInt()).size} ")
    var foldedDots=dots
    folds.forEach { (axis,pos)->
        foldedDots = fold(foldedDots,axis,pos.toInt())
    }

    println("Day 13, part 2: The code is:")
    val colRange= 0..foldedDots.maxOf { it.first }
    val rowRange= 0..foldedDots.maxOf { it.second }
    rowRange.forEach { row ->
        colRange.forEach { col -> if (foldedDots.contains(Pair(col,row))) print('|') else print(' ')}
        println()
    }
}