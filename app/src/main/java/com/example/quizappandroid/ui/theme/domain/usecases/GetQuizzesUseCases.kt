package com.example.quizappandroid.ui.theme.domain.usecases

import com.example.quizappandroid.ui.theme.commom.Resource
import com.example.quizappandroid.ui.theme.domain.model.Quiz
import com.example.quizappandroid.ui.theme.domain.repository.QuizRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.flow

class GetQuizzesUseCases (
    val quizRepository: QuizRepository
) {

    operator fun invoke(amount: Int,
                   category: Int,
                   difficulty: String,
                   type: String
    ) : Flow<Resource<List<Quiz>>> = flow {

        emit(Resource.Loading())

        try {
            emit(Resource.Success(data = quizRepository.getQuizzes(amount, category, difficulty, type)))
        }
        catch(e : Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}