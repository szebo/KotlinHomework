package minesweeper.logic

import minesweeper.windowing.GamePanel
import minesweeper.windowing.SQUARE_MARGIN
import minesweeper.windowing.SQUARE_SIZE
import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.Graphics2D
import java.util.Random

class Field(private val width: Int,
            private val height: Int,
            private val numberOfMines: Int) {

    private var field = Array(height){ Array(width){ Square(0, isMine = false, isFlagged = false, isRevealed = false) } }

    var gameLost = false
    var gameWon = false
    private var flaggedSquares = 0
    private var revealedSquares = 0

    init {
        generate()
    }

    fun isGameEnded(): Boolean{
        gameWon = flaggedSquares+revealedSquares == width*height || revealedSquares == width*height-numberOfMines
        return gameLost || gameWon
    }

    private fun generate(){
        var currentMines = 0
        val r = Random()
        while(currentMines < numberOfMines){
            for(y in 0..< height){
                for(x in 0..< width){
                    if(!field[y][x].isMine && r.nextInt(10)==1) {
                        field[y][x].isMine = true
                        currentMines++
                        updateSurroundingsOf(x, y)
                    }
                }
            }
        }
    }

    fun draw(g: Graphics){
        val g2 = g as Graphics2D
        for(y in 0..< height)
            for(x in 0..< width)
                field[y][x].draw(g2, x, y)
    }

    fun revealSquare(x: Int, y: Int){
        if(field[y][x].isMine) gameLost = true
        else if(field[y][x].minesAround < 1) revealEmptySpace(x, y)
        field[y][x].isRevealed = true
        revealedSquares++
    }

    private fun revealEmptySpace(x: Int, y:Int){
        if(x < 0 || x >= width || y < 0 || y >= height) return
        if(field[y][x].isFlagged || field[y][x].isRevealed) return
        field[y][x].isRevealed = true
        revealedSquares++
        if(field[y][x].minesAround > 0) return
        revealEmptySpace(x-1, y-1)
        revealEmptySpace(x, y-1)
        revealEmptySpace(x+1, y-1)
        revealEmptySpace(x-1, y)
        revealEmptySpace(x+1, y)
        revealEmptySpace(x-1, y+1)
        revealEmptySpace(x, y+1)
        revealEmptySpace(x+1, y+1)
    }

    fun flagSquare(x: Int, y: Int){
        field[y][x].isFlagged = !field[y][x].isFlagged
        if(field[y][x].isFlagged) flaggedSquares++
        else flaggedSquares--
    }

    private fun updateSurroundingsOf(x: Int, y: Int){
        if(x == 0 && y == 0){
            field[y][x+1].minesAround++
            field[y+1][x].minesAround++
            field[y+1][x+1].minesAround++
        }
        else if(x == width-1 && y == 0){
            field[y][x-1].minesAround++
            field[y+1][x].minesAround++
            field[y+1][x-1].minesAround++
        }
        else if(x == 0 && y == height-1){
            field[y][x+1].minesAround++
            field[y-1][x].minesAround++
            field[y-1][x+1].minesAround++
        }
        else if(x == width-1 && y == height-1){
            field[y][x-1].minesAround++
            field[y-1][x].minesAround++
            field[y-1][x-1].minesAround++
        }
        else if(y == 0){
            field[y][x-1].minesAround++
            field[y+1][x-1].minesAround++
            field[y+1][x].minesAround++
            field[y+1][x+1].minesAround++
            field[y][x+1].minesAround++
        }
        else if(x == 0){
            field[y-1][x].minesAround++
            field[y-1][x+1].minesAround++
            field[y][x+1].minesAround++
            field[y+1][x+1].minesAround++
            field[y+1][x].minesAround++
        }
        else if(y == height-1){
            field[y][x-1].minesAround++
            field[y-1][x-1].minesAround++
            field[y-1][x].minesAround++
            field[y-1][x+1].minesAround++
            field[y][x+1].minesAround++
        }
        else if(x == width-1){
            field[y-1][x].minesAround++
            field[y-1][x-1].minesAround++
            field[y][x-1].minesAround++
            field[y+1][x-1].minesAround++
            field[y+1][x].minesAround++
        }
        else{
            field[y-1][x-1].minesAround++
            field[y-1][x].minesAround++
            field[y-1][x+1].minesAround++
            field[y][x-1].minesAround++
            field[y][x+1].minesAround++
            field[y+1][x-1].minesAround++
            field[y+1][x].minesAround++
            field[y+1][x+1].minesAround++
        }
    }
}