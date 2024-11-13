package com.example.quizappandroid.ui.theme.presentation.quiz.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizappandroid.ui.theme.presentation.util.Dimens

@Preview
@Composable
fun ShimmerEffectQuizInterface() {
    Column {
        Row(
            modifier = Modifier.padding(Dimens.SmallPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(28.dp)
                    .height(40.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .weight(1f)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.width(5.dp))
            Box(
                modifier = Modifier
                    .width(20.dp)
                    .height(40.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .weight(9f)
                    .shimmerEffect()
            )
        }
        Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))
        Column(modifier = Modifier.padding(horizontal = 15.dp)) {
            repeat(4) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimens.MediumBoxHeight)
                        .clip(RoundedCornerShape(Dimens.LargeCornerRadius))
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
            }
            Spacer(modifier = Modifier.height(Dimens.ExtraLargeSpacerHeight))
            Row {
                Box(
                    modifier = Modifier
                        .weight(0.5f)
                        .fillMaxWidth()
                        .height(Dimens.MediumBoxHeight)
                        .clip(RoundedCornerShape(Dimens.LargeCornerRadius))
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.width(Dimens.SmallSpacerWidth))
                Box(
                    modifier = Modifier
                        .weight(0.5f)
                        .fillMaxWidth()
                        .height(Dimens.MediumBoxHeight)
                        .clip(RoundedCornerShape(Dimens.LargeCornerRadius))
                        .shimmerEffect()
                )
            }
        }
    }
}

fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition(label = "")
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    ).value
    background(color = androidx.compose.ui.graphics.Color(0xFF03DAC5).copy(alpha = alpha))
}