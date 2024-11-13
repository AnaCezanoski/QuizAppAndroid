package com.example.quizappandroid.ui.theme.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.quizappandroid.ui.theme.presentation.common.AppDropDownMenu
import com.example.quizappandroid.ui.theme.presentation.common.ButtonBox
import com.example.quizappandroid.ui.theme.presentation.util.Dimens.MediumPadding
import com.example.quizappandroid.ui.theme.presentation.util.Dimens.MediumSpacerHeight
import com.example.quizappandroid.ui.theme.presentation.util.Dimens.SmallSpacerHeight
import com.example.quizappandroid.ui.theme.presentation.home.component.HomeHeader
import com.example.quizappandroid.ui.theme.presentation.nav_graph.Routes
import com.example.quizappandroid.ui.theme.presentation.util.Constants

//@Preview
//@Composable
//fun PrevHome() {
//    HomeScreen(state = StateHomeScreen(), event = {})
//}

@Composable
fun HomeScreen(
    state : StateHomeScreen,
    event : (EventHomeScreen) -> Unit,
    navController: NavController

) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HomeHeader()

        Spacer(modifier = Modifier.height(MediumSpacerHeight))
        AppDropDownMenu(
            menuName = "Number of questions: ",
            menuList = Constants.numbersAsString,
            text = state.numberOfQuiz.toString(),
            onDropDownClick = {event(EventHomeScreen.SetNumberOfQuizzes(it.toInt()))}
        )

        Spacer(modifier = Modifier.height(SmallSpacerHeight))
        AppDropDownMenu(
            menuName = "Select category: ",
            menuList = Constants.categories,
            text = state.category.toString(),
            onDropDownClick = {event(EventHomeScreen.SetQuizCategory(it))}
        )

        Spacer(modifier = Modifier.height(SmallSpacerHeight))
        AppDropDownMenu(
            menuName = "Difficulty level: ",
            menuList = Constants.difficulty,
            text = state.difficulty.toString(),
            onDropDownClick = {event(EventHomeScreen.SetQuizDifficulty(it))}
        )

        Spacer(modifier = Modifier.height(SmallSpacerHeight))
        AppDropDownMenu(
            menuName = "Select Type: ",
            menuList = Constants.type,
            text = state.type.toString(),
            onDropDownClick = {event(EventHomeScreen.SetQuizType(it))}
        )

        Spacer(modifier = Modifier.height(MediumSpacerHeight))

        ButtonBox(text = "Generate quiz", padding = MediumPadding) {
            Log.d("quiz detail", "${state.numberOfQuiz}")
            navController.navigate(
                route = Routes.QuizScreen.passQuizParams(
                    state.numberOfQuiz,
                    state.category,
                    state.difficulty,
                    state.type
                )
            )
        }
    }
}