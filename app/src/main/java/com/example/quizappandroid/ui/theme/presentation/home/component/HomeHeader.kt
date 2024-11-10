package com.example.quizappandroid.ui.theme.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizappandroid.R // Use o R do seu pacote, não android.R
import com.example.quizappandroid.ui.theme.presentation.util.Dimens

@Preview
@Composable
fun HomeHeader() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .height(Dimens.HomeTopBoxHeight)
            .background(
                color = colorResource(id = R.color.purple_700),
                shape = RoundedCornerShape(
                    bottomStart = Dimens.ExtraLargeCornerRadius,
                    bottomEnd = Dimens.ExtraLargeCornerRadius
                ),
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimens.LargePadding),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "",
                modifier = Modifier
                    .weight(1f)
                    .size(Dimens.MediumIconSize),
                tint = colorResource(id = R.color.teal_200)
            )
            Text(
                text = "Quiz App",
                color = colorResource(id = R.color.teal_200),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(3.5f),
                textAlign = TextAlign.Center,
                fontSize = Dimens.LargeTextSize
            )

            Icon(
                painter = painterResource(id = R.drawable.baseline_account_box_24),
                contentDescription = "",
                modifier = Modifier
                    .weight(1f)
                    .size(Dimens.MediumIconSize),
                tint = colorResource(id = R.color.teal_200)
            )
        }
    }
}
