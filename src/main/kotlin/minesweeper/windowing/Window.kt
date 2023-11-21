package minesweeper.windowing

import java.awt.CardLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JPanel

const val WINDOW_HEIGHT = 600
const val WINDOW_WIDTH = 600

object Window : JFrame(), ActionListener{

    private val windowPanel: JPanel = JPanel(CardLayout())

    private val mainMenu = MainMenu(this)
    private val setupMenu = SetupMenu(this)

    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT)
        add(windowPanel)
        windowPanel.add(mainMenu, "mainMenu")
        windowPanel.add(setupMenu, "setupMenu")
        setLocationRelativeTo(null)
    }

    private fun inSetupMenu(){
        val cards = windowPanel.layout as CardLayout
        cardsChanged(setupMenu)
        cards.show(windowPanel, "setupMenu")
    }

    private fun inMainMenu(){
        val cards = windowPanel.layout as CardLayout
        cardsChanged(mainMenu)
        cards.show(windowPanel, "mainMenu")
    }

    override fun actionPerformed(e: ActionEvent?) {
        val button = e?.source as JButton

        when(button.text){
            "Back" -> inMainMenu()
            "Start" -> inSetupMenu()
            "Play" -> setupMenu.startGame()
        }
    }

    private fun cardsChanged(current: JPanel) {
        mainMenu.isVisible = false
        setupMenu.isVisible = false
        current.isVisible = true
    }
}