package com.example.quizappandroid.ui.theme.presentation.leaderboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LeaderboardScreen(viewModel: LeaderboardViewModel = hiltViewModel()) {
    val leaderboard by viewModel.leaderboardEntries.collectAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Leaderboard", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(16.dp))

        leaderboard.forEachIndexed { index, entry ->
            Text(text = "${index + 1}. ${entry.username} - ${entry.score}")
        }
    }
}