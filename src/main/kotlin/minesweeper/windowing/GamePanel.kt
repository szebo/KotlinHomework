package minesweeper.windowing

import minesweeper.logic.Field
import java.awt.Color
import java.awt.Graphics
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.JPanel

const val SQUARE_SIZE = 30
const val SQUARE_MARGIN = 2

class GamePanel(mWidth: Int, mHeight: Int, mMines: Int) : JPanel(), MouseListener {

    private val field = Field(mWidth, mHeight, mMines)

    init {
        addMouseListener(this)
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        g?.color = Color.DARK_GRAY
        g?.fillRect(0, 0, width, height)
        field.draw(g!!)
    }

    override fun mouseClicked(e: MouseEvent?) {
        val clickedX = e?.x?.div(SQUARE_SIZE+ SQUARE_MARGIN)
        val clickedY = e?.y?.div(SQUARE_SIZE+ SQUARE_MARGIN)
        println("X:$clickedX Y:$clickedY")
        if(e?.button == MouseEvent.BUTTON3){
            field.flagSquare(clickedX!!, clickedY!!)
        }
        else if(e?.button == MouseEvent.BUTTON1){
            field.revealSquare(clickedX!!, clickedY!!)
        }
        if(field.isGameEnded())

        repaint()
    }

    override fun mousePressed(e: MouseEvent?) {

    }

    override fun mouseReleased(e: MouseEvent?) {

    }

    override fun mouseEntered(e: MouseEvent?) {

    }

    override fun mouseExited(e: MouseEvent?) {

    }

}