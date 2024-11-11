package com.example.quizappandroid.ui.theme.data.di

import com.example.quizappandroid.ui.theme.data.remote.QuizApi
import com.example.quizappandroid.ui.theme.data.repository.QuizRepositoryImpl
import com.example.quizappandroid.ui.theme.domain.repository.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    @Singleton
    fun provideQuizApi() : QuizApi {
        return Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuizApi::class.java)
    }

    @Provides
    @Singleton
    fun provideQuizResponse(quizApi: QuizApi) : QuizRepository {
        return QuizRepositoryImpl(quizApi)
    }
}