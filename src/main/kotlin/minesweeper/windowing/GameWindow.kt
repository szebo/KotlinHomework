package minesweeper.windowing

import javax.swing.JFrame

class GameWindow(mWidth: Int, mHeight: Int, mMines: Int) : JFrame(){

    private val gamePanel = GamePanel(mWidth, mHeight, mMines)

    init {
        defaultCloseOperation = DISPOSE_ON_CLOSE
        setSize((mWidth+1)*(SQUARE_SIZE + SQUARE_MARGIN), (mHeight+1)*(SQUARE_SIZE + 2*SQUARE_MARGIN)+70)
        add(gamePanel)
        setLocationRelativeTo(null)
    }

}