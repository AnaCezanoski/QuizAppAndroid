package com.example.quizappandroid.ui.theme.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizappandroid.ui.theme.presentation.util.Dimens
import java.lang.reflect.Modifier

@Preview
@Composable
fun Prev() {
    val list = listOf("Item 1", "Iten 2")
    AppDropDomMenu(menuName = "Drop Down", menulist = list)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDropDownMenu(
    menuName: String,
    menuList: List<String>,
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    var selectedText by remember {
        mutableStateOf(menuList[0])
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.MediumPadding)
    ) {
        Text(

            text = menuName,
            style = MaterialTheme.typography.titleMedium,
            fontiWeight = FontWeight.Bold,
            color = colorResource(id = R.color.teal_200),
            )

        Spacer (modifier = Modifier.height(SmallSpacerHeight))

        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded isExpanded}  the Exposed Dropdown&tenuBoxScope OutlinedTextField( modifier Modifier menuAnchor() fillMaxWidth(). value selectedText.
    }

}