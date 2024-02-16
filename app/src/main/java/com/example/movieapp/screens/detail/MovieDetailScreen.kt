package com.example.movieapp.screens.detail

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.R
import com.example.movieapp.utils.Constants
import com.example.movieapp.utils.ImageState

@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieDetailScreen() {

    var state by remember {
        mutableStateOf(ImageState.LOADING)
    }

    val infiniteTransition = rememberInfiniteTransition()
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.app_name)) },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                navigationIcon = {
                    IconButton(onClick = {

                    }) {

                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Toggle drawer"
                        )
                    }
                },
            )
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(10.dp), verticalArrangement = Arrangement.Top
        ) {

            val title = "Wonka dahskjdha asdajgsdjha adkahjs"
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier
                        .size(45.dp)
                        .padding(end = 10.dp),
                    painter = painterResource(id = R.drawable.icon_movie),
                    contentDescription = "Icon Review"
                )
                Column() {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            title,
                            modifier = if(title.length > 25)  Modifier.weight(1f) else Modifier,
                            style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Image(
                            painter = painterResource(id = R.drawable.ic_star),
                            contentDescription = "Rating"
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            "7.2",
                            style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            "/10",
                            style = MaterialTheme.typography.overline.copy(fontWeight = FontWeight.Medium)
                        )
                    }
                    Text(text = "2023-12-06 | Comedy, Family, Fantasy")
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/w500/qhb1qOilapbapxWQn9jtRCMwXJF.jpg")
                    .crossfade(true).build(),
                placeholder = painterResource(id = R.drawable.ic_refresh),
                error = painterResource(id = R.drawable.ic_broken),
                onLoading = {
                    state = ImageState.LOADING
                },
                onError = {
                    state = ImageState.ERROR
                },
                onSuccess = {
                    state = ImageState.SUCCESS
                },
                alignment = Alignment.Center,
                contentDescription = "Movie Image",
                contentScale = if (state == ImageState.SUCCESS) ContentScale.Crop else ContentScale.None,
                modifier = if (state == ImageState.LOADING) Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .graphicsLayer {
                        rotationZ = progress
                    } else Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }
    }

}