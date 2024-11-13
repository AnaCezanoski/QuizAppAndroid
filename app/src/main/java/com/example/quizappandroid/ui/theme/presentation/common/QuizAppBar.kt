package com.example.quizappandroid.ui.theme.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.quizappandroid.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizAppBar(
    quizCategory: String,
    onBackClick: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = colorResource(id = R.color.purple_700),
            actionIconContentColor = colorResource(id = R.color.purple_700),
            navigationIconContentColor = colorResource(id = R.color.purple_700),
        ),

        title = {
            Text(
                text = quizCategory,
                color = colorResource(id = R.color.purple_700),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },

        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                    contentDescription = ""
                )
            }
        },
    )
}