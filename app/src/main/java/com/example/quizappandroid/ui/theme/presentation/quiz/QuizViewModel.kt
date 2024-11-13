package com.example.quizappandroid.ui.theme.presentation.quiz.component

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizappandroid.ui.theme.commom.Resource
import com.example.quizappandroid.ui.theme.domain.model.Quiz
import com.example.quizappandroid.ui.theme.domain.usecases.GetQuizzesUseCases
import com.example.quizappandroid.ui.theme.presentation.quiz.EventQuizScreen
import com.example.quizappandroid.ui.theme.presentation.quiz.QuizState
import com.example.quizappandroid.ui.theme.presentation.quiz.StateQuizScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(private val getQuizzesUseCases: GetQuizzesUseCases) : ViewModel() {

    private val _quizList = MutableStateFlow(StateQuizScreen())
    val quizList = _quizList

    fun onEvent(event : EventQuizScreen) {
        when (event) {
            is EventQuizScreen.GetQuizzes -> {
                getQuizzes(event.numOfQuizzes, event.category, event.difficulty, event.type)
            }
            is EventQuizScreen.SetOptionSelected -> {
                updateQuizStateList(event.quizStateIndex, event.selectedOption)
            }

            else -> {}
        }
    }

    private fun updateQuizStateList(quizStateIndex: Int, selectedOption: Int) {

        val updatedQuizStateList = mutableListOf<QuizState>()

        quizList.value.quizState.forEachIndexed { index, quizState ->
            updatedQuizStateList.add(
                if (index == quizStateIndex) {
                    quizState.copy(selectedOptions = selectedOption)
                }
                else quizState
            )
        }
        _quizList.value = _quizList.value.copy(quizState = updatedQuizStateList)

        updateScore(_quizList.value.quizState[quizStateIndex])
    }

    private fun updateScore(quizState: QuizState) {
        if(quizState.selectedOptions != -1){
            val correctAnswer = quizState.quiz?.correct_answer
            val selectedAnswer = quizState.selectedOptions?.let {
                quizState.shuffledOptions[it].replace("&quot;", "\"").replace("&#039;", "\'")
            }
            Log.d("check", "$correctAnswer -> $selectedAnswer")
            if(correctAnswer == selectedAnswer){
                val previousScore = _quizList.value.score
                _quizList.value = _quizList.value.copy(score = previousScore + 1)
            }
        }
    }

    private fun getQuizzes(amount: Int, category : Int, difficulty : String, type : String) {
        viewModelScope.launch {
            getQuizzesUseCases(amount, category, difficulty, type).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        Log.d("quiz", "Loading...")
                        _quizList.value = StateQuizScreen(isLoading = true)
                    }

                    is Resource.Success -> {
                        val listOfQuizState : List<QuizState> = getListOfQuizState(resource.data)
                        _quizList.value = StateQuizScreen(quizState = listOfQuizState)
                    }

                    is Resource.Error -> {
                        _quizList.value = StateQuizScreen(error = resource.message.toString())
                    }
                    else -> {}
                }
            }
        }
    }
    private fun getListOfQuizState(data: List<Quiz>?): List<QuizState> {
        val listOfQuizState = mutableStateListOf<QuizState>()

        for (quiz in data!!) {

            val shuffledOptions = mutableListOf<String>().apply {
                add(quiz.correct_answer)
                addAll(quiz.incorrect_answers)
                shuffle()
            }

            listOfQuizState.add((QuizState(quiz, shuffledOptions, -1)))
        }
        return listOfQuizState
    }
}