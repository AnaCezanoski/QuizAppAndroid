package com.example.quizappandroid.ui.theme.presentation.quiz.component

import android.R
import android.R.attr.text
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizappandroid.ui.theme.domain.model.Quiz
import com.example.quizappandroid.ui.theme.presentation.common.ButtonBox
import com.example.quizappandroid.ui.theme.presentation.common.QuizAppBar
import com.example.quizappandroid.ui.theme.presentation.quiz.EventQuizScreen
import com.example.quizappandroid.ui.theme.presentation.quiz.StateQuizScreen
import com.example.quizappandroid.ui.theme.presentation.util.Constants
import com.example.quizappandroid.ui.theme.presentation.util.Dimens
import com.example.quizappandroid.ui.theme.presentation.util.Dimens.LargeSpacerHeight
import com.example.quizappandroid.ui.theme.presentation.util.Dimens.MediumCornerRadius
import com.example.quizappandroid.ui.theme.presentation.util.Dimens.MediumPadding

@Preview
@Composable
fun Prevquiz() {
    QuizScreen(
        numOfQuiz = 10,
        quizCategory = "General Knowledge",
        quizDifficulty = "Easy",
        quizType = "easy",
        event = {},
        state = StateQuizScreen())
}

@Composable
fun QuizScreen(
    numOfQuiz: Int,
    quizCategory: String,
    quizDifficulty: String,
    quizType: String,
    event: (EventQuizScreen) -> Unit,
    state: StateQuizScreen
) {
    LaunchedEffect(key1 = Unit) {
        val difficulty = when (quizDifficulty) {
            "Medium" -> "medium"
            "Hard" -> "hard"
            else -> "easy"
        }

        val type = when (quizType) {
            "Multiple Choice" -> "multiple"
            else -> "boolean"
        }
        Log.d("quiz", "choice")
        event(EventQuizScreen.GetQuizzes(numOfQuiz, Constants.categoriesMap[quizCategory]!!, difficulty, type))
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        QuizAppBar(quizCategory = quizCategory) {}

        Column(
            modifier = Modifier
                .padding(Dimens.VerySmallPadding)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(LargeSpacerHeight))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Questions : $numOfQuiz",
                )
                Text(
                    text = quizDifficulty,
                    color = colorResource(id = R.color.holo_blue_light)
                )
            }

            Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.VerySmallViewHeight)
                    .clip(RoundedCornerShape(MediumCornerRadius))
                    .background(
                        color = colorResource(id = R.color.holo_blue_light)
                    )
            )

            Spacer(modifier = Modifier.height(LargeSpacerHeight))

            QuizInterface(modifier = Modifier.weight(1f), onOptionSelected = {}, qNumber = 1)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = MediumPadding)
                    .navigationBarsPadding()
            ) {
                ButtonBox(
                    text = "Previous",
                    padding = Dimens.SmallPadding,
                    fraction = 0.43f,
                    fontSize = Dimens.SmallTextSize
                ) {

                }

                Spacer(modifier = Modifier.weight(0.1f))

                ButtonBox(
                    text = "Next",
                    padding = Dimens.SmallPadding,
                    borderColor = colorResource(id = R.color.holo_purple),
                    containerColor = colorResource(id = R.color.holo_blue_dark),
                    fraction = 1f,
                    textColor = colorResource(id = R.color.white),
                    fontSize = Dimens.SmallTextSize
                ) {

                }
            }
        }
    }
}
