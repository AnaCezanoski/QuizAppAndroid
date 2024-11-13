package com.example.quizappandroid.ui.theme.domain.di

import com.example.quizappandroid.ui.theme.domain.repository.QuizRepository
import com.example.quizappandroid.ui.theme.domain.usecases.GetQuizzesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    @Singleton
    fun provideGetQuizzesUseCases(quizRepository: QuizRepository) : GetQuizzesUseCases {
        return GetQuizzesUseCases(quizRepository)
    }
}