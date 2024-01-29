package com.example.movieapp.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.R
import com.example.movieapp.model.Movie
import com.example.movieapp.screens.home.HomeViewModel
import com.example.movieapp.utils.Constants
import com.example.movieapp.utils.ImageState

@Composable
fun MovieItem(
    movie: Movie,
    viewModel: HomeViewModel,
    navController: NavController,
    isFavorite: Boolean = false
) {
    Surface(
        Modifier
            .fillMaxWidth()
            .shadow(ambientColor = Color.Blue, elevation = 15.dp)
            .padding(4.dp)
            .aspectRatio(0.4F),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 3.dp
    ) {

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

        Column {
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data("${Constants.IMAGE_URL}${movie.posterPath}")
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
            Text(
                text = movie.title ?: "No Title",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                    .height(55.dp)
                    .wrapContentHeight(Alignment.CenterVertically),
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = movie.releaseDate ?: "No Title",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Image(
                    modifier = Modifier.size(35.dp).clickable {

                    },
                    painter = painterResource(id = R.drawable.ic_question),
                    contentDescription = "Detail"
                )
                Spacer(modifier = Modifier.width(20.dp))
                Image(
                    modifier = Modifier.size(35.dp).clickable {
                        if(isFavorite){
                            viewModel.deleteFavorite(movie)
                        }else if(movie.isFavorite){
                            viewModel.deleteFavorite(movie)
                        }else{
                            viewModel.insertFavorite(movie)
                        }
                    },
                    painter = painterResource(id = if(isFavorite) R.drawable.fill_star else if (movie.isFavorite) R.drawable.fill_star else R.drawable.outline_star),
                    contentDescription = "Favorite"
                )
                Spacer(modifier = Modifier.width(20.dp))
                Image(
                    modifier = Modifier.size(35.dp).clickable {

                    },
                    painter = painterResource(id = R.drawable.ic_overview),
                    contentDescription = "Sinopsis"
                )
            }
        }

    }
}