package minesweeper.logic

import minesweeper.windowing.SQUARE_MARGIN
import minesweeper.windowing.SQUARE_SIZE
import java.awt.Color
import java.awt.Font
import java.awt.Graphics2D

data class Square(
    var minesAround: Int,
    var isMine: Boolean,
    var isFlagged: Boolean,
    var isRevealed: Boolean
){
    fun draw(g2: Graphics2D, x: Int, y: Int){
        if(isRevealed) {
            g2.color = Color(180, 180, 180)
            g2.fillRect(
                x * (SQUARE_SIZE + SQUARE_MARGIN),
                y * (SQUARE_SIZE + SQUARE_MARGIN),
                SQUARE_SIZE,
                SQUARE_SIZE)
            if(minesAround > 0) {
                g2.color = getColor()
                g2.font = Font("Times New Roman", Font.BOLD, 25)
                g2.drawString(
                    minesAround.toString(),
                    x * (SQUARE_SIZE + SQUARE_MARGIN) + SQUARE_MARGIN,
                    y * (SQUARE_SIZE + SQUARE_MARGIN) + SQUARE_SIZE - SQUARE_MARGIN)
            }
        }
        else if(isFlagged){
            g2.color = Color(180, 180, 180)
            g2.fillRect(
                x * (SQUARE_SIZE + SQUARE_MARGIN),
                y * (SQUARE_SIZE + SQUARE_MARGIN),
                SQUARE_SIZE,
                SQUARE_SIZE)

            g2.color = Color.BLACK
            g2.drawString("F",
                x * (SQUARE_SIZE + SQUARE_MARGIN) + SQUARE_MARGIN,
                y * (SQUARE_SIZE + SQUARE_MARGIN) + SQUARE_SIZE - SQUARE_MARGIN)
        }
        else if(isMine && isRevealed){
            g2.color = Color.RED
            g2.drawString("X",
                x * (SQUARE_SIZE + SQUARE_MARGIN) + SQUARE_MARGIN,
                y * (SQUARE_SIZE + SQUARE_MARGIN) + SQUARE_SIZE - SQUARE_MARGIN)
        }
        else {
            g2.color = Color.GRAY
            g2.fillRect(x * (SQUARE_SIZE + SQUARE_MARGIN), y * (SQUARE_SIZE + SQUARE_MARGIN), SQUARE_SIZE, SQUARE_SIZE)
        }
    }

    private fun getColor(): Color{
        when(minesAround){
            1-> return Color.BLUE
            2-> return Color.GREEN
            3-> return Color.RED
            4-> return Color.YELLOW
            5-> return Color.CYAN
            6-> return Color.PINK
            7-> return Color.ORANGE
            8-> return Color.MAGENTA
            else -> return Color.WHITE
        }
    }
}
