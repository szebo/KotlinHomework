package minesweeper.windowing

import minesweeper.logic.LeaderboardManager
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.event.ActionListener
import javax.swing.JButton
import javax.swing.JPanel
import kotlin.system.exitProcess

class MainMenu(listener: ActionListener) : JPanel() {

    private val btStart = JButton("Start")
    private val btLeaderboard = JButton("Leaderboard")
    private val btExit = JButton("Exit")

    init{
        btStart.addActionListener(listener)
        btExit.addActionListener{
            LeaderboardManager.saveLeaderboardFile()
            exitProcess(1)
        }
        btLeaderboard.addActionListener(listener)

        layout = GridBagLayout()
        var constraints = GridBagConstraints()
        constraints.gridx = 1
        constraints.gridy = 0
        this.add(btStart, constraints)
        constraints.gridy = GridBagConstraints.RELATIVE
        add(btLeaderboard, constraints)
        add(btExit, constraints)
    }

}