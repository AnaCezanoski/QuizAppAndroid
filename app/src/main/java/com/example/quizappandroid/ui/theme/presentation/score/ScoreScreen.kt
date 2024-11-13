package com.example.quizappandroid.ui.theme.presentation.score

import android.icu.text.DecimalFormat
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.quizappandroid.R
import com.example.quizappandroid.ui.theme.presentation.nav_graph.Routes
import com.example.quizappandroid.ui.theme.presentation.util.Dimens

//@Preview
//@Composable
//fun PrevScore() {
//    ScoreScreen(numOfQuestions = 16, numOfCorrectAnswer = 3)
//}

@Composable
fun ScoreScreen(
    numOfQuestions: Int,
    numOfCorrectAnswer: Int,
    navController: NavController
) {
    BackHandler {
        goToHome(navController)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.MediumPadding),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = {
                    goToHome(navController = navController)

                },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_close_24),
                    contentDescription = "close",
                    tint = colorResource(id = R.color.purple_700)
                )
            }
        }

        Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .clip(RoundedCornerShape(Dimens.MediumCornerRadius))
                .background(colorResource(id = R.color.teal_200)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = Dimens.MediumPadding,
                    vertical = Dimens.MediumPadding
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.congra))
                val annotatedString = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append("You attempted ")
                    }
                    withStyle(style = SpanStyle(color = Color.Blue)) {
                        append("$numOfQuestions questions")
                    }
                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append(" and from that ")
                    }
                    withStyle(style = SpanStyle(color = colorResource(id = R.color.purple_700))) {
                        append("$numOfCorrectAnswer answers")
                    }
                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append(" are correct")
                    }
                }

                val scorePercentage = calculatePercentage(numOfCorrectAnswer, numOfQuestions)
                LottieAnimation(
                    modifier = Modifier.size(Dimens.LargeLottieAnimSize),
                    composition = composition,
                    iterations = 100
                )
                Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

                Text(
                    text = "Congrats!",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimens.MediumTextSize,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))

                Text(
                    text = "$scorePercentage% Score",
                    color = colorResource(id = R.color.purple_700),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimens.LargeTextSize,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))

                Text(
                    text = "Quiz completed successfully.",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimens.SmallTextSize,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))

                Text(
                    text = annotatedString,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimens.SmallTextSize,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))

            }
        }
    }
}

fun goToHome(navController: NavController) {
    navController.navigate(Routes.HomeScreen.route) {
        popUpTo(Routes.HomeScreen.route) {
            inclusive = true
        }
    }
}

fun calculatePercentage(correctAnswers: Int, totalQuestions: Int): Double {
    require(correctAnswers >= 0 && totalQuestions > 0) { "Invalid input: correctAnswers must be non-negative and totalQuestions must be positive" }
    val percentage = (correctAnswers.toDouble() / totalQuestions.toDouble()) * 100.0
    return DecimalFormat("#.##").format(percentage).toDouble()
}
