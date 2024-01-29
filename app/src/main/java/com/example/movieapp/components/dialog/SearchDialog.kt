package com.example.movieapp.components.dialog

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.movieapp.components.InputText
import com.example.movieapp.components.TextButton

@Composable
fun SearchDialog(controller: MutableState<Boolean>, valueState: MutableState<String>, onAction: () -> Unit){
    Row(verticalAlignment = Alignment.CenterVertically) {
        InputText(
            modifier = Modifier.weight(2f),
            valueState = valueState,
            labelId = "Search",
            enabled = true,
            iconLeading = Icons.Rounded.Search,
            keyboardType = KeyboardType.Text
        )
        TextButton(
            modifier = Modifier.weight(1f).padding(end = 10.dp),
            signLabel = "Search",
            onClickButton = onAction
        )
    }
}