package com.example.quizappandroid.ui.theme.presentation.quiz

import android.R
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.quizappandroid.ui.theme.presentation.common.ButtonBox
import com.example.quizappandroid.ui.theme.presentation.common.QuizAppBar
import com.example.quizappandroid.ui.theme.presentation.nav_graph.Routes
import com.example.quizappandroid.ui.theme.presentation.quiz.component.QuizInterface
import com.example.quizappandroid.ui.theme.presentation.quiz.component.ShimmerEffectQuizInterface
import com.example.quizappandroid.ui.theme.presentation.util.Constants
import com.example.quizappandroid.ui.theme.presentation.util.Dimens
import com.example.quizappandroid.ui.theme.presentation.util.Dimens.LargeSpacerHeight
import com.example.quizappandroid.ui.theme.presentation.util.Dimens.MediumCornerRadius
import com.example.quizappandroid.ui.theme.presentation.util.Dimens.MediumPadding
import kotlinx.coroutines.launch

//@Preview
//@Composable
//fun PrevQuiz() {
//    QuizScreen(
//        numOfQuiz = 10,
//        quizCategory = "General Knowledge",
//        quizDifficulty = "Easy",
//        quizType = "easy",
//        event = {},
//        state = StateQuizScreen(),
//        navController = NavController(LocalContext.current)
//    )
//}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizScreen(
    numOfQuiz: Int,
    quizCategory: String,
    quizDifficulty: String,
    quizType: String,
    event: (EventQuizScreen) -> Unit,
    state: StateQuizScreen,
    navController: NavController
) {

    BackHandler {
        navController.navigate(Routes.HomeScreen.route) {
            popUpTo(Routes.HomeScreen.route) {
                inclusive = true
            }
        }
    }

    LaunchedEffect(key1 = quizDifficulty) {
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
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
    ) {
        QuizAppBar(quizCategory = quizCategory) {
            navController.navigate(Routes.HomeScreen.route) {
                popUpTo(Routes.HomeScreen.route) {
                    inclusive = true
                }
            }
        }

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
                Text(text = "Questions : $numOfQuiz")
                Text(
                    text = quizDifficulty,
                    color = colorResource(id = R.color.white)
                )
            }

            Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.VerySmallViewHeight)
                    .clip(RoundedCornerShape(MediumCornerRadius))
                    .background(color = colorResource(id = R.color.white))
            )

            Spacer(modifier = Modifier.height(LargeSpacerHeight))

            val pageState = rememberPagerState(initialPage = 0) { state.quizState.size }

            if (quizFetched(state)) {
                HorizontalPager(state = pageState) { index ->
                    QuizInterface(
                        modifier = Modifier.weight(1f),
                        quizState = state.quizState[index],
                        onOptionSelected = {selectedIndex ->
                            event(EventQuizScreen.SetOptionSelected(index, selectedIndex))
                        },
                        qNumber = index + 1
                    )
                }

                val buttonText = when (pageState.currentPage) {
                    0 -> listOf("", "Next")
                    state.quizState.size - 1 -> listOf("Previous", "Submit")
                    else -> listOf("Previous", "Next")
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = MediumPadding)
                        .navigationBarsPadding()
                ) {
                    val scope = rememberCoroutineScope()

                    if (buttonText[0].isNotEmpty()) {
                        ButtonBox(
                            text = "Previous",
                            padding = Dimens.SmallPadding,
                            fraction = 0.43f,
                            fontSize = Dimens.MediumTextSize
                        ) {
                            scope.launch { pageState.animateScrollToPage(pageState.currentPage - 1) }
                        }
                    } else {
                        ButtonBox(
                            text = "",
                            fraction = 0.43f,
                            fontSize = Dimens.SmallTextSize,
                            borderColor = colorResource(id = R.color.white),
                            containerColor = colorResource(id = R.color.white),
                        ) { }
                    }

                    ButtonBox(
                        text = buttonText[1],
                        padding = Dimens.SmallPadding,
                        borderColor = colorResource(id = R.color.holo_blue_dark),
                        containerColor = if (pageState.currentPage == state.quizState.size - 1) colorResource(id = R.color.holo_blue_dark)
                        else colorResource(id = R.color.holo_blue_dark),
                        fraction = 1f,
                        textColor = colorResource(id = R.color.white),
                        fontSize = Dimens.MediumTextSize
                    ) {
                        if (pageState.currentPage == state.quizState.size - 1) {
                            navController.navigate(Routes.ScoreScreen.passNumQuestionsAndCorrectAns(state.quizState.size, state.score))

                        } else {
                            scope.launch { pageState.animateScrollToPage(pageState.currentPage + 1) }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun quizFetched(state: StateQuizScreen): Boolean {
    return when {
        state.isLoading -> {
            ShimmerEffectQuizInterface()
            false
        }
        state.quizState.isNotEmpty() -> {
            true
        }
        else -> {
            Text(text = state.error.toString(), color = colorResource(id = R.color.white))
            false
        }
    }
}
