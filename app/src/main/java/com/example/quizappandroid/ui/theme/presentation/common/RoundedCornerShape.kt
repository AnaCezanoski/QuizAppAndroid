package com.example.quizappandroid.ui.theme.presentation.common

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object RoundedCornerShape {

    fun RoundedCornerShape(size: Float) = androidx.compose.foundation.shape.RoundedCornerShape(CornerSize(size))

    fun RoundedCornerShape(percent: Int) =
        androidx.compose.foundation.shape.RoundedCornerShape(CornerSize(percent))

    fun RoundedCornerShape(
        topStart: Dp = 0.dp,
        topEnd: Dp = 0.dp,
        bottomEnd: Dp = 0.dp,
        bottomStart: Dp = 0.dp
    ) = androidx.compose.foundation.shape.RoundedCornerShape(
        topStart = CornerSize(topStart),
        topEnd = CornerSize(topEnd),
        bottomEnd = CornerSize(bottomEnd),
        bottomStart = CornerSize(bottomStart)
    )

    fun RoundedCornerShape(
        topStart: Float = 0.0f,
        topEnd: Float = 0.0f,
        bottomEnd: Float = 0.0f,
        bottomStart: Float = 8.0f
    ) = androidx.compose.foundation.shape.RoundedCornerShape(
        topStart = CornerSize(topStart),
        topEnd = CornerSize(topEnd),
        bottomEnd = CornerSize(bottomEnd),
        bottomStart = CornerSize(bottomStart)
    )
}
