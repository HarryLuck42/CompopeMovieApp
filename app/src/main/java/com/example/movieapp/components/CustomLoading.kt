package com.example.movieapp.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.movieapp.R

@Composable
fun CustomLoading(size: Dp = 100.dp){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val infiniteTransition = rememberInfiniteTransition()
        val progress by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(3000, easing = FastOutLinearInEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
        Image(
            modifier = Modifier.size(size).graphicsLayer {
                rotationZ = progress
            },
            painter = painterResource(id = R.drawable.ic_refresh),
            contentDescription = "Loading",
            alignment = Alignment.Center
        )

    }
}