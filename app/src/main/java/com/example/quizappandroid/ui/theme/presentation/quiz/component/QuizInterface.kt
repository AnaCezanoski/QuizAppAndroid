package com.example.quizappandroid.ui.theme.presentation.quiz.component

import android.R
import android.R.attr.text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizappandroid.ui.theme.presentation.quiz.QuizOption
import com.example.quizappandroid.ui.theme.presentation.util.Dimens

@Preview
@Composable
fun Prevv(){
    QuizInterface(
    onOptionSelected = {}, qNumber = 1)
}
@Composable
fun QuizInterface(
    onOptionSelected: (Int) -> Unit,
    qNumber: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.wrapContentHeight()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "$qNumber.",
                    color = colorResource(id = com.example.quizappandroid.R.color.teal_200),
                    fontSize = Dimens.SmallTextSize
                )

                Text(
                    modifier = Modifier.weight(9f),
                    text = "Which cartoon do you see?",
                    color = colorResource(id = com.example.quizappandroid.R.color.teal_200),
                    fontSize = Dimens.MediumTextSize
                )
            }
            Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))

            val options = listOf(
                "A" to "Doraemon",
                "B" to "Oggy",
                "C" to "Ben 10",
                "D" to "Tom and Jerry"
            )

            Column(modifier = Modifier.padding(horizontal = 15.dp)) {
                options.forEachIndexed { index, (optionNumber, optionText) ->
                    if (optionText.isNotEmpty()) {
                        QuizOption(
                            optionNumber = optionNumber,
                            options = optionText,
                            selected = false,
                            onOptionClick = { onOptionSelected(index) },
                            onUnselectOption = { onOptionSelected(-1) }
                        )
                    }
                    Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
                }
            }
        }
    }
}
