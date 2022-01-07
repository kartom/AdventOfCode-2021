fun parseLine(lines: MutableList<String>, c: Char): MutableList<Int> =
    lines.first().replace("  "," ").trim().
    split(c).map { it.toInt() }.also { lines.removeFirst() }.toMutableList()

class BingoBoard( lines: MutableList<String> ) {
    private val _numbers = mutableListOf<MutableList<Int>>()
    var score = 0

    init {
        while(if(lines.isNotEmpty()) lines.first().isNotEmpty() else false)
           _numbers.add(parseLine(lines,' '))
    }

    fun drawNumber(number: Int) {
        for (r in _numbers.indices) for (c in _numbers.first().indices) {
            if (_numbers[r][c] == number) {
                _numbers[r][c] = -1
                if (!_numbers.indices.map { _numbers[it][c]==-1 }.contains(false)  // Col bingo
                    or !_numbers[r].map { it==-1 }.contains(false)) //Row bingo
                    score = number*(_numbers.sumOf { row -> row.sumOf { if (it >= 0) it else 0 } })
            }
        }
    }
}

fun playUntil(boards: MutableList<BingoBoard>, numbers: MutableList<Int>, nofBingo: Int) {
    while ((boards.count { it.score>0 }<nofBingo ) and numbers.isNotEmpty()) {
        boards.forEach { it.drawNumber(numbers.first()) }
        numbers.removeFirst()
    }
}

fun main()  {
    val lines = java.io.File("data/Day-04-data.txt").readLines().toMutableList()
    val numbers = parseLine(lines,',')
    val boards = mutableListOf<BingoBoard>()
    while( if(lines.isNotEmpty()) lines.first().isBlank() else false ) {
        lines.removeFirst()
        boards.add(BingoBoard(lines))
    }

    playUntil(boards, numbers, 1)
    println("Day 4, part 1: Score of first bingo:  ${boards.sumOf { it.score }}")

    playUntil(boards, numbers, boards.size-1)
    val lastBoard = boards.find { it.score == 0 }
    while( (lastBoard!!.score == 0) and numbers.isNotEmpty() ) lastBoard.drawNumber(numbers.removeFirst())
    println("Day 4, part 2: Score of last bingo:  ${lastBoard.score}")
}
