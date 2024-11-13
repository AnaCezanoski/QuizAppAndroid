package com.example.quizappandroid.ui.theme.presentation.leaderboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.quizappandroid.ui.theme.presentation.leaderboard.dao.LeaderboardDao
import kotlinx.coroutines.launch

class LeaderboardViewModel(application: Application) : AndroidViewModel(application) {
    private val leaderboardDao: LeaderboardDao = LeaderboardDatabase.getDatabase(application).leaderboardDao()
    private val _leaderboardEntries = MutableLiveData<List<LeaderboardEntry>>()
    val leaderboardEntries: LiveData<List<LeaderboardEntry>> get() = _leaderboardEntries

    fun getLeaderboard() {
        viewModelScope.launch {
            _leaderboardEntries.postValue(leaderboardDao.getTopLeaderboardEntries())
        }
    }

    fun addLeaderboardEntry(playerName: String, score: Int, timestamp: Long) {
        viewModelScope.launch {
            val entry = LeaderboardEntry(playerName = playerName, score = score, timestamp = timestamp)
            leaderboardDao.insertLeaderboardEntry(entry)
            getLeaderboard()
        }
    }

    fun clearLeaderboard() {
        viewModelScope.launch {
            leaderboardDao.clearLeaderboard()
        }
    }
}