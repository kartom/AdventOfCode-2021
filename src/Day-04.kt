import java.io.File

fun parseLine(lines: MutableList<String>, delimiter: Char): List<Int> {
    val line = lines.first().replace("  "," ").trim()
    val numbers = line.split(delimiter).map { it.toInt() }
    lines.removeFirst()
    return numbers
}


class BingoBoard() {
    private val _numbers = mutableListOf<List<Int>>()
    private val _drawn = mutableListOf<MutableList<Boolean>>()

    fun readBoard( lines: MutableList<String>) {
        while(lines.isNotEmpty()) {
            if( lines.first().isNotEmpty()) {
                _numbers.add(parseLine(lines, ' '))
                _drawn.add(MutableList(_numbers.last().size) {false})
            } else break
        }
    }

    fun resetBoard() {
        for(r in _drawn.indices) {
            for(c in _drawn[r].indices) {
                _drawn[r][c]=false
            }
        }
    }

    fun printBoard() {
        for(r in _numbers.indices) {
            for(c in _numbers[r].indices) {
                print( _numbers[r][c].toString().padStart(3))
                if( _drawn[r][c] ) print("*") else print (" ")
            }
            println()
        }
    }

    fun drawNumber(number: Int): Boolean {
        val colBingo = MutableList<Boolean>(_numbers.first().size) { true }
        val rowBingo = MutableList<Boolean>(_numbers.size) { true }
        for (r in _numbers.indices) {
            for (c in _numbers[r].indices) {
                if (_numbers[r][c] == number) _drawn[r][c]=true
                if(!_drawn[r][c]) {
                    colBingo[c]=false
                    rowBingo[r]=false
                }
            }
        }
        for( bingo in colBingo ) if(bingo) return true
        for( bingo in rowBingo ) if(bingo) return true
        return false
    }

    fun sumOfUndrawn(): Int {
        var sum =0
        for (r in _numbers.indices) {
            for (c in _numbers[r].indices) {
                if(!_drawn[r][c]) sum+= _numbers[r][c]
            }
        }
        return sum
    }
}

fun day4Part1(numbers: List<Int>, boards: MutableList<BingoBoard>) {
    print("Numbers:")
    for( number in numbers) {
        print(" $number")
        for(board in boards) {
            val bingo= board.drawNumber(number)
            // if( bingo ) print("BINGO! ")
            // println("....................")
            // board.printBoard()
            if( bingo ) {
                println(" BINGO!")
                val sum = board.sumOfUndrawn()
                println("Sum of board: $sum")
                println("Score: $sum*$number = ${sum*number}")
                return
            }

        }
    }
}


fun day4Part2(numbers: List<Int>, boards: MutableList<BingoBoard>) {
    print("Numbers:")
    val bingo = MutableList<Boolean>(boards.size) { false }
    for( number in numbers) {
        for(i in boards.indices) {
            if( boards[i].drawNumber(number) )
                if( !bingo[i] ) {
                    bingo[i] = true
                    // boards[i].printBoard()
                }
        }
        print(" $number(${bingo.count { it }})")
        if( boards.size-bingo.count { it } == 1 ) {
            //The board without bingo is the last board to get bingo, find it!
            println(" Only one board left!")
            println("Replaying the last board (just to keep it simple)")
            for(i in boards.indices) {
                if(!bingo[i]) {
                    //This is the board, lets play this one alone
                    boards[i].printBoard()
                    day4Part1(numbers, mutableListOf(boards[i]))
                    return
                }
            }
        }
    }
}

fun main()  {
    //Preparations
    val lines = File("data/Day-04-data.txt").readLines().toMutableList()
    val numbers = parseLine(lines,',')
    println("Numbers: $numbers")
    val boards = mutableListOf<BingoBoard>()
    while( lines.isNotEmpty() ) {
        if( lines.first().isBlank() ) {
            lines.removeFirst()
            boards.add(BingoBoard())
            boards.last().readBoard(lines)
        }
        else {
            lines.removeFirst()
        }
    }
    println("=================================\nDay 4 - part 1")
    day4Part1(numbers,boards)
    println("=================================\nDay 4 - part 2")
    boards.map { it.resetBoard() }
    day4Part2(numbers,boards)
}
