package com.example.quizappandroid.ui.theme.presentation.quiz

import com.example.quizappandroid.ui.theme.domain.model.Quiz

data class StateQuizScreen(
    val isLoading : Boolean = false,
    val quizState: List<QuizState> = emptyList(),
    val error: String = "",
    val score: Int = 0
)

data class QuizState(
    val quiz : Quiz ? = null,
    val shuffledOptions : List<String> = emptyList(),
    val selectedOptions : Int ? = -1
)