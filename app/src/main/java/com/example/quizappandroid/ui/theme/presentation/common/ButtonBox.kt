package com.example.quizappandroid.ui.theme.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.example.quizappandroid.R
import com.example.quizappandroid.ui.theme.presentation.util.Dimens
import com.example.quizappandroid.ui.theme.presentation.util.Dimens.MediumTextSize
import com.example.quizappandroid.ui.theme.presentation.util.Dimens.SmallBorderWidth

//@Preview
//@Composable
//fun PrevBB() {
//    ButtonBox(text = "Generate Quiz", padding = Dimens.SmallPadding)
//}

@Preview
@Composable
fun PrevButtonBox() {
//    ButtonBox(
//    text = "Next",
//    borderColor = colorResource(id = R.color.purple_500),
//        containerColor = colorResource(id = R.color.purple_700),
//        textColor = colorResource(id = R.color.teal_200)
//    ) { }
    ButtonBox(
        text = "Next",
    ) { }
}

@Composable
fun ButtonBox(
    text: String,
    padding: Dp = Dimens.SmallPadding,
    borderColor : Color = colorResource(id = R.color.purple_700),
    containerColor : Color = colorResource(id = R.color.purple_700),
    textColor : Color = colorResource(id = R.color.white),
    fontSize: TextUnit = MediumTextSize,
    fraction : Float = 1f,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(padding)
            .border(SmallBorderWidth, borderColor, RoundedCornerShape(Dimens.LargeCornerRadius))
            .fillMaxWidth(fraction)
            .clickable { onClick() }
            .height(Dimens.MediumBoxHeight)
            .clip(RoundedCornerShape(Dimens.LargeCornerRadius))
            .background(containerColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = fontSize,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = textColor
        )
    }
}