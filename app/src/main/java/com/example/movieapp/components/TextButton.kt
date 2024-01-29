package com.example.movieapp.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TextButton(
    modifier: Modifier = Modifier,
    signLabel: String = "+",
    onClickButton: () -> Unit = {},
) {

    Button(onClick = {
        if (signLabel == "-"){

            onClickButton()
        }else {
            onClickButton()
        }

        // Log.d("TAG", "CustomButton: ${count}")
    },
        modifier = modifier
            .width(40.dp)
            .height(40.dp),
        colors  = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF7E54A0))) {
        Text(text = signLabel,
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.SemiBold)
        )

    }

}