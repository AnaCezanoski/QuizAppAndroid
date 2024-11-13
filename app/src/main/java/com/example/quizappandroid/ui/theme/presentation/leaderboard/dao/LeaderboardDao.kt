package com.example.quizappandroid.ui.theme.presentation.leaderboard.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quizappandroid.ui.theme.presentation.leaderboard.LeaderboardEntry

@Dao
interface LeaderboardDao {
    @Insert
    suspend fun insertLeaderboardEntry(entry: LeaderboardEntry)

    @Query("SELECT * FROM leaderboard ORDER BY score DESC, timestamp ASC LIMIT 10")
    suspend fun getTopLeaderboardEntries(): List<LeaderboardEntry>

    @Query("DELETE FROM leaderboard")
    suspend fun clearLeaderboard()
}