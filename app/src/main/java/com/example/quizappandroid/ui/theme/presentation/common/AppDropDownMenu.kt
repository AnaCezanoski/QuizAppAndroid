package com.example.quizappandroid.ui.theme.presentation.common

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizappandroid.ui.theme.presentation.util.Dimens
import com.example.quizappandroid.ui.theme.presentation.util.Dimens.SmallSpacerHeight

@Preview
@Composable
fun Prev() {
    val list = listOf("Item 1", "Item 2")
    AppDropDownMenu(menuName = "Drop Down", menuList = list)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDropDownMenu(
    menuName: String,
    menuList: List<String>,
) {
    var isExpanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(menuList[0]) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.MediumPadding)
    ) {
        Text(
            text = menuName,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.holo_blue_light),
        )

        Spacer(modifier = Modifier.height(SmallSpacerHeight))

        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded }
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                colors = TextFieldDefaults.outlinedTextFieldColors (
                    focusedTextColor = colorResource(id = R.color.holo_blue_light),
                    unfocusedTrailingIconColor = colorResource(id = R.color.holo_purple),
                    focusedTrailingIconColor = colorResource(id = R.color.holo_purple),
                    focusedBorderColor = colorResource(id = R.color.holo_blue_dark),
                    unfocusedBorderColor = colorResource(id = R.color.holo_blue_dark),
                    containerColor = colorResource(id = R.color.holo_blue_dark)
                ),
                shape = RoundedCornerShape(15.dp)
            )

            DropdownMenu(
                modifier = Modifier.background(
                    colorResource(id = R.color.holo_purple)
                ),
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false },
            ) {
                menuList.forEachIndexed { index: Int, text: String ->
                    DropdownMenuItem(
                        text = { Text(text = text, color = colorResource(id = R.color.holo_blue_light)) },
                        onClick = {
                            selectedText = menuList[index]
                            isExpanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
}
