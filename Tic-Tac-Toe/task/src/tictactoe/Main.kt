package tictactoe

fun symbWins(myGrid: Array<Array<Char>>, symb: Char):Boolean {
    return  myGrid[0][0] == symb && myGrid[0][1] == symb && myGrid[0][2] == symb ||
            myGrid[1][0] == symb && myGrid[1][1] == symb && myGrid[1][2] == symb ||
            myGrid[2][0] == symb && myGrid[2][1] == symb && myGrid[2][2] == symb ||
            myGrid[0][0] == symb && myGrid[1][0] == symb && myGrid[2][0] == symb ||
            myGrid[0][1] == symb && myGrid[1][1] == symb && myGrid[2][1] == symb ||
            myGrid[0][2] == symb && myGrid[1][2] == symb && myGrid[2][2] == symb ||
            myGrid[0][0] == symb && myGrid[1][1] == symb && myGrid[2][2] == symb ||
            myGrid[0][2] == symb && myGrid[1][1] == symb && myGrid[2][0] == symb
}

fun countSymb(myGrid: Array<Array<Char>>, symb: Char): Int {
    var counter = 0
    for (i in 0..2) {
        for (j in 0..2) {
            if (myGrid[i][j] == symb) { counter++ }
        }
    }
    return counter
}

fun printGrid(myGrid: Array<Array<Char>>) {
    println("---------")
    println("| ${myGrid[0][0]} ${myGrid[0][1]} ${myGrid[0][2]} |")
    println("| ${myGrid[1][0]} ${myGrid[1][1]} ${myGrid[1][2]} |")
    println("| ${myGrid[2][0]} ${myGrid[2][1]} ${myGrid[2][2]} |")
    println("---------")
}

fun main() {
    var grid = Array(3) {Array(3) {' '} }
    printGrid(grid)
    var player = true
    do {
        do {
            var inputOk = true
            print("Enter the coordinates: ")
            val myString = readLine()!!
            if (!(' ' in myString)) {
                println("You should enter numbers!")
                inputOk = false
                continue
            }
            val (x, y) = myString.split(" ").map { it[0] }
            if (x.isDigit() && y.isDigit()) {
                val xNum = x.toString().toInt()
                val yNum = y.toString().toInt()
                if (xNum in 1..3 && yNum in 1..3) {
                    if (grid[xNum - 1][yNum - 1] == ' ' || grid[xNum - 1][yNum - 1] == '_') {
                        grid[xNum - 1][yNum - 1] = if (player) { 'X' } else { 'O' }
                        player = !player
                        inputOk = true
                    } else {
                        println("This cell is occupied! Choose another one!")
                        inputOk = false
                    }
                } else {
                    println("Coordinates should be from 1 to 3!")
                    inputOk = false
                }
            } else {
                println("You should enter numbers!")
                inputOk = false
            }
        } while (!inputOk)
        printGrid(grid)
        if (symbWins(grid, 'X')) {
            println("X wins")
            break
        } else if (symbWins(grid, 'O')) {
            println("O wins")
            break
        } else if (countSymb(grid, ' ') == 0) {
            println("Draw")
            break
        }
    } while (true)
}