package com.example.movieapp.screens.home.fragments.favorites

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.components.EmptyLayout
import com.example.movieapp.components.MovieItem
import com.example.movieapp.screens.home.HomeViewModel

@Composable
fun FavoritesFragment(viewModel: HomeViewModel, navController: NavController) {
    val movieData = viewModel.favorite.collectAsState().value

    if (movieData.isEmpty()) {
        EmptyLayout()
    } else {
        LazyVerticalGrid(
            modifier = Modifier.padding(2.dp),
            contentPadding = PaddingValues(2.dp),
            columns = GridCells.Fixed(2)
        ) {
            items(movieData.size) {
                val movie = movieData[it]
                MovieItem(movie, viewModel, navController, true)

            }
        }
    }
}