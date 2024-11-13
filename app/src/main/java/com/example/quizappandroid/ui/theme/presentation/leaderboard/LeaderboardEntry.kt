package com.example.quizappandroid.ui.theme.presentation.leaderboard

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leaderboard")
data class LeaderboardEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "player_name") val playerName: String,
    @ColumnInfo(name = "score") val score: Int,
    @ColumnInfo(name = "timestamp") val timestamp: Long
)