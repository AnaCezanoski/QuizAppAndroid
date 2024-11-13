package com.example.quizappandroid.ui.theme.presentation.quiz.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizappandroid.R
import com.example.quizappandroid.ui.theme.presentation.quiz.QuizOption
import com.example.quizappandroid.ui.theme.presentation.quiz.QuizState
import com.example.quizappandroid.ui.theme.presentation.util.Dimens
import kotlin.random.Random
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.core.text.HtmlCompat

//@Preview
//@Composable
//fun Prev(){
//    QuizInterface(
//    onOptionSelected = {}, qNumber = 1, quizState = QuizState())
//}

@Composable
fun QuizInterface(
    onOptionSelected: (Int) -> Unit,
    qNumber: Int,
    quizState: QuizState,
    modifier: Modifier = Modifier
) {
    val randomImageIndex = Random.nextInt(1, 4)
    val questionMarkImage = when (randomImageIndex) {
        1 -> R.drawable.question_mark_1
        2 -> R.drawable.question_mark_2
        3 -> R.drawable.question_mark_3
        else -> R.drawable.question_mark_1
    }
    val question = HtmlCompat.fromHtml(quizState.quiz?.question ?: "", HtmlCompat.FROM_HTML_MODE_LEGACY)
    val decodedQuestion = question.toString().replace("&quot;", "\"").replace("&#039;", "\'")

    val scrollState = rememberScrollState()

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .heightIn(max = 800.dp)
                .verticalScroll(scrollState)
        ) {
            Image(
                painter = painterResource(id = questionMarkImage),
                contentDescription = "Question Mark",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

            Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "$qNumber.",
                    color = colorResource(id = R.color.purple_700),
                    fontSize = Dimens.MediumTextSize
                )

                Text(
                    modifier = Modifier.weight(9f),
                    text = decodedQuestion,
                    color = colorResource(id = R.color.purple_700),
                    fontSize = Dimens.MediumTextSize
                )
            }

            Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))

            val options = listOf(
                "A" to HtmlCompat.fromHtml(quizState.shuffledOptions[0], HtmlCompat.FROM_HTML_MODE_LEGACY),
                "B" to HtmlCompat.fromHtml(quizState.shuffledOptions[1], HtmlCompat.FROM_HTML_MODE_LEGACY),
                "C" to HtmlCompat.fromHtml(quizState.shuffledOptions[2], HtmlCompat.FROM_HTML_MODE_LEGACY),
                "D" to HtmlCompat.fromHtml(quizState.shuffledOptions[3], HtmlCompat.FROM_HTML_MODE_LEGACY),
            )

            Column(modifier = Modifier.padding(horizontal = 15.dp)) {
                options.forEachIndexed { index, (optionNumber, optionText) ->
                    if (optionText.isNotEmpty()) {
                        QuizOption(
                            optionNumber = optionNumber,
                            options = optionText.toString(),
                            onOptionClick = { onOptionSelected(index) },
                            selected = quizState.selectedOptions == index,
                            onUnselectOption = { onOptionSelected(-1) }
                        )
                    }
                    Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
                }
            }
        }
    }
}
