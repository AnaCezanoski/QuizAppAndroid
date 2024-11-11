package com.example.quizappandroid.ui.theme.domain.repository

import com.example.quizappandroid.ui.theme.domain.model.Quiz

interface QuizRepository {

    suspend fun getQuizzes(
        amount : Int,
        category : Int,
        difficulty : String,
        type : String) : List<Quiz>
}