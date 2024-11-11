package com.example.quizappandroid.ui.theme.data.repository

import com.example.quizappandroid.ui.theme.data.remote.QuizApi
import com.example.quizappandroid.ui.theme.domain.model.Quiz
import com.example.quizappandroid.ui.theme.domain.repository.QuizRepository

class QuizRepositoryImpl(
    private val quizApi: QuizApi
) : QuizRepository {
    override suspend fun getQuizzes(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String
    ): List<Quiz> {
        return quizApi.getQuizzes(amount, category, difficulty, type).results
    }
}