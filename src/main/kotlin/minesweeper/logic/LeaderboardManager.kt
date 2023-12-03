package minesweeper.logic

import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.util.Date
import kotlin.math.min

object LeaderboardManager {
    data class GameResult(
        val time: Int,
        val width: Int,
        val height: Int,
        val mines: Int,
        val date: Date,
        val score: Int
    )

    private var games = mutableListOf<GameResult>()

    fun addNewResult(time: Int, width: Int, height: Int, mines: Int, date: Date){
        val score = ((mines/width*height)*1000)/time
        games.add(GameResult(time, width, height, mines, date, score))
        games.sortedWith(compareByDescending { it.score })
    }

    fun loadLeaderboardFile(){
        val path = this.javaClass.classLoader.getResource("leaderboard.txt")?.path!!
        val file = File(path).bufferedReader().readLines()
        println(path+" file loaded "+file.size)
        games.clear()
        for(line in file){
            val currentLine = line.split(";")
            games.add(GameResult(currentLine[0].toInt(), currentLine[1].toInt(), currentLine[2].toInt(), currentLine[3].toInt(), Date(currentLine[4].toLong()), currentLine[5].toInt()))
        }
    }

    fun saveLeaderboardFile(){
        val path = this.javaClass.classLoader.getResource("leaderboard.txt")?.path!!
        val file = FileWriter(path)
        file.write("")
        for(i in 0..min(games.size-1, 9)){
            file.appendLine("${games[i].time};${games[i].width};${games[i].height};${games[i].mines};${games[i].date.time.toLong()};${games[i].score}")
        }
        file.close()
    }

    fun getLeaderboard(): Array<Array<Any?>>{
        var array = Array(games.size){Array<Any?>(6) { null } }
        println(games.size)
        for(i in 0..min(games.size-1, 9)){
            array[i] = arrayOf(games[i].time, games[i].width, games[i].height, games[i].mines, games[i].date, games[i].score)
        }

        return array
    }
}