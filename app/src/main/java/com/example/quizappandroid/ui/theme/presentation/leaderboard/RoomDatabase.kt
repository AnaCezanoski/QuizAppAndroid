package com.example.quizappandroid.ui.theme.presentation.leaderboard

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.quizappandroid.ui.theme.presentation.leaderboard.dao.LeaderboardDao

@Database(entities = [LeaderboardEntry::class], version = 1)
abstract class LeaderboardDatabase : RoomDatabase() {
    abstract fun leaderboardDao(): LeaderboardDao

    companion object {
        @Volatile
        private var INSTANCE: LeaderboardDatabase? = null

        fun getDatabase(context: Context): LeaderboardDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LeaderboardDatabase::class.java,
                    "leaderboard_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}