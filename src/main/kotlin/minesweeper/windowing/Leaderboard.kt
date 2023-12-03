package minesweeper.windowing

import minesweeper.logic.LeaderboardManager
import java.awt.BorderLayout
import java.awt.event.ActionListener
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JTable
import javax.swing.table.TableModel

class Leaderboard(listener: ActionListener) : JPanel() {

    private val columnNames = arrayOf("Time", "Width", "Height", "Mines", "Date", "Score")
    private val btReturn = JButton("Return")
    private lateinit var table: JTable

    init {
        LeaderboardManager.loadLeaderboardFile()
        btReturn.addActionListener(listener)
        layout = BorderLayout()
        refresh()
        add(table.tableHeader, BorderLayout.PAGE_START)
        add(table, BorderLayout.CENTER)
        add(btReturn, BorderLayout.SOUTH)
    }

    fun refresh(){
        val data = LeaderboardManager.getLeaderboard()
        table = JTable(data, columnNames)
        table.fillsViewportHeight = true
        repaint()
    }
}