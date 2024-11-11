package com.example.quizappandroid.ui.theme.presentation.quiz

import com.example.quizappandroid.ui.theme.domain.model.Quiz

data class StateQuizScreen(
    val isLoading : Boolean = false,
    val data: List<Quiz> ? = listOf(),
    val error: String = ""
)