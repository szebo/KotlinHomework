package minesweeper.windowing

import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import java.awt.event.ActionListener
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

class SetupMenu(listener: ActionListener) : JPanel() {

    private val btBack = JButton("Back")
    private val btPlay = JButton("Play")

    private val widthLabel = JLabel("Width")
    private val heightLabel = JLabel("Height")
    private val minesLabel = JLabel("Mines")
    private val widthInput = JTextField(5)
    private val heightInput = JTextField(5)
    private val minesInput = JTextField(5)

    init {
        btPlay.addActionListener(listener)
        btBack.addActionListener(listener)

        layout = GridBagLayout()
        var constraints = GridBagConstraints()
        constraints.gridx = 1
        constraints.gridy = 0
        add(widthLabel, constraints)
        constraints.gridx = GridBagConstraints.RELATIVE
        add(widthInput, constraints)
        constraints.gridx = 1
        constraints.gridy = 1
        add(heightLabel, constraints)
        constraints.gridx = GridBagConstraints.RELATIVE
        add(heightInput, constraints)
        constraints.gridx = 1
        constraints.gridy = 2
        add(minesLabel, constraints)
        constraints.gridx = GridBagConstraints.RELATIVE
        add(minesInput, constraints)
        constraints.insets = Insets(20, 5, 0, 5)
        constraints.gridx = 1
        constraints.gridy = 3
        add(btPlay, constraints)
        constraints.gridx = GridBagConstraints.RELATIVE
        add(btBack, constraints)
    }

    fun startGame(){
        var gameWindow = GameWindow(widthInput.text.toInt(), heightInput.text.toInt(), minesInput.text.toInt())
        gameWindow.isVisible = true
    }
}