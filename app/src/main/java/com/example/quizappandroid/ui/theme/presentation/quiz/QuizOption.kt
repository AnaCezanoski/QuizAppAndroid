package com.example.quizappandroid.ui.theme.presentation.quiz

import android.util.Log
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.AnimationConstants.DefaultDurationMillis
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizappandroid.R
import com.example.quizappandroid.ui.theme.presentation.util.Dimens.MediumTextSize
import com.example.quizappandroid.ui.theme.presentation.util.Dimens.SmallCircleShape
import com.example.quizappandroid.ui.theme.presentation.util.Dimens.SmallSpacerWidth

// Adicione a função noRippleClickable aqui
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null
    ) { onClick() }
}

@Preview
@Composable
fun Prev() {
    QuizOption(
        optionNumber = "A",
        options = "tanto faz, tanto faz, tanto faz",
        selected = false,
        onOptionClick = {},
        onUnselectOption = {},
    )
}

@Composable
fun QuizOption(
    optionNumber: String,
    options: String,
    selected: Boolean,
    onOptionClick: () -> Unit,
    onUnselectOption: () -> Unit
) {
    val optionTextColor = if (selected) colorResource(id = R.color.teal_200) else colorResource(id = com.example.quizappandroid.R.color.black)
    val transition = updateTransition(selected, label = "selected")

    val startColor = transition.animateColor(
        transitionSpec = { tween(durationMillis = DefaultDurationMillis, easing = LinearEasing) },
        label = "startColor"
    ) { selectedBox ->
        if (selectedBox) colorResource(id = R.color.purple_200)
        else colorResource(id = R.color.teal_200)
    }.value

    Box(
        modifier = Modifier
            .noRippleClickable { onOptionClick() }
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = startColor, shape = RoundedCornerShape(16.dp))
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (!selected) {
                Box(
                    modifier = Modifier
                        .size(SmallCircleShape)
                        .shadow(10.dp, CircleShape, clip = true)
                        .clip(CircleShape)
                        .background(colorResource(id = com.example.quizappandroid.R.color.purple_200)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = optionNumber,
                        fontWeight = FontWeight.Bold,
                        fontSize = MediumTextSize,
                        color = colorResource(id = com.example.quizappandroid.R.color.teal_200),
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                Spacer(modifier = Modifier.size(SmallCircleShape))
            }

            Spacer(modifier = Modifier.width(SmallSpacerWidth))

            Text(
                modifier = Modifier.weight(1f),
                text = options,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                maxLines = 3,
                color = optionTextColor
            )

            if (selected) {
                Box(
                    modifier = Modifier
                        .shadow(10.dp, CircleShape, clip = true)
                        .clip(CircleShape)
                        .background(colorResource(id = com.example.quizappandroid.R.color.teal_200)),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = { onUnselectOption() }) {
                        Icon(
                            painter = painterResource(id = com.example.quizappandroid.R.drawable.baseline_close_24),
                            contentDescription = "close",
                            tint = colorResource(id = com.example.quizappandroid.R.color.purple_500)
                        )
                    }
                }
            }
        }
    }
}
