package com.example.quizappandroid.ui.theme.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizappandroid.ui.theme.presentation.common.AppDropDownMenu
import com.example.quizappandroid.ui.theme.presentation.common.ButtonBox
import com.example.quizappandroid.ui.theme.presentation.util.Dimens.MediumPadding
import com.example.quizappandroid.ui.theme.presentation.util.Dimens.MediumSpacerHeight
import com.example.quizappandroid.ui.theme.presentation.util.Dimens.SmallSpacerHeight
import com.example.quizappandroid.ui.theme.presentation.home.component.HomeHeader
import com.example.quizappandroid.ui.theme.presentation.util.Constants


@Preview
@Composable
fun HomeScreen() {

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
        )

        Spacer(modifier = Modifier.height(SmallSpacerHeight))
        AppDropDownMenu(
            menuName = "Select category: ",
            menuList = Constants.categories
        )

        Spacer(modifier = Modifier.height(SmallSpacerHeight))
        AppDropDownMenu(
            menuName = "Difficulty level: ",
            menuList = Constants.difficulty
        )

        Spacer(modifier = Modifier.height(SmallSpacerHeight))
        AppDropDownMenu(
            menuName = "Select Type: ",
            menuList = Constants.type
        )

        Spacer(modifier = Modifier.height(MediumSpacerHeight))

        ButtonBox(text = "Generate quiz", padding = MediumPadding)
        }
    }

