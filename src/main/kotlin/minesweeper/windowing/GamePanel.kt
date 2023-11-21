package minesweeper.windowing

import minesweeper.logic.Field
import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.text.Format
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.Timer

const val SQUARE_SIZE = 30
const val SQUARE_MARGIN = 2

class GamePanel(mWidth: Int, mHeight: Int, mMines: Int) : JPanel(), MouseListener, ActionListener {

    private val field = Field(mWidth, mHeight, mMines)

    private val gameEndedLabel = JLabel()
    private val timerLabel = JLabel("0:0")
    private val timer = Timer(1000, this)
    private var seconds = 0
    private var minutes = 0

    init {
        addMouseListener(this)
        timerLabel.font = Font("Times new roman", Font.PLAIN, 30)
        add(timerLabel)
        gameEndedLabel.isEnabled = false
        gameEndedLabel.font = Font("Times new roman", Font.PLAIN, 30)
        add(gameEndedLabel)
        timer.start()
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        g?.color = Color.DARK_GRAY
        g?.fillRect(0, 0, width, height)
        field.draw(g!!)
    }

    private fun endGame(){
        timer.stop()
        gameEndedLabel.text = "<html><font color='white'>${if(field.gameLost) "You lost" else "You won"}</font></html>"
        gameEndedLabel.isEnabled = true

    }

    override fun actionPerformed(e: ActionEvent?) {
        seconds++
        if(seconds >= 60){
            minutes++
            seconds = 0
        }
        timerLabel.text = "<html><font color='white'>${minutes}:${seconds}</font></html>"
    }

    override fun mouseClicked(e: MouseEvent?) {
        val clickedX = e?.x?.div(SQUARE_SIZE+ SQUARE_MARGIN)
        val clickedY = e?.y?.minus(70)?.div(SQUARE_SIZE+ SQUARE_MARGIN)
        println("X:$clickedX Y:$clickedY")
        if(e?.button == MouseEvent.BUTTON3){
            field.flagSquare(clickedX!!, clickedY!!)
        }
        else if(e?.button == MouseEvent.BUTTON1){
            field.revealSquare(clickedX!!, clickedY!!)
        }
        if(field.isGameEnded())
            endGame()

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