package com.example.quizappandroid.ui.theme.presentation.score

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizappandroid.ui.theme.presentation.util.Dimens

@Preview
@Composable
fun PrevScore() {
    ScoreScreen(numOfQuestion = 16, numOfCorrectAnswer = 3)
}

@Composable
fun ScoreScreen(
    numOfQuestion: Int,
    numOfCorrectAnswer: Int,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.MediumPadding),
        verticalArrangement = Arrangement.Center
    ) {

    }

}