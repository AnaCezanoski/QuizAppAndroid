package com.example.quizappandroid.ui.theme.data.remote.dto

import com.example.quizappandroid.ui.theme.domain.model.Quiz

data class QuizResponse(
    val response_code: Int,
    val results: List<Quiz>
)