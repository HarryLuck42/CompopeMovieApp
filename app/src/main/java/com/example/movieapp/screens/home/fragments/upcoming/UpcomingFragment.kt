package com.example.movieapp.screens.home.fragments

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.components.CustomLoading
import com.example.movieapp.components.EmptyLayout
import com.example.movieapp.components.MovieItem
import com.example.movieapp.model.response.MovieResponse
import com.example.movieapp.screens.home.HomeViewModel
import com.example.movieapp.utils.Constants
import com.example.movieapp.utils.DataOrException

@Composable
fun UpcomingFragment(viewModel: HomeViewModel, navController: NavController) {
    Surface(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val favorites = viewModel.favorite.collectAsState().value

        val movieData =
            if (viewModel.keyword.value.isNotEmpty() && !viewModel.openSearch.value) produceState<DataOrException<MovieResponse, Boolean, Exception>>(
                initialValue = DataOrException(loading = true)
            ) {
                value = viewModel.searchMovies("1", viewModel.keyword.value)
            }.value else produceState<DataOrException<MovieResponse, Boolean, Exception>>(
                initialValue = DataOrException(loading = true)
            ) {
                value = viewModel.getAllMovies(Constants.UPCOMING_PATH, "1")
            }.value

        if (movieData.loading == true) {
            CustomLoading()
        } else {
            val movies = movieData.data?.results

            if (movies.isNullOrEmpty()) {
                EmptyLayout()
            } else {
                for(fav in favorites){
                    for(v in movies){
                        val i = movies.indexOf(v)
                        if(v.id == fav.id){
                            movies[i].isFavorite = true
                        }
                    }
                }
                LazyVerticalGrid(
                    modifier = Modifier.padding(2.dp),
                    contentPadding = PaddingValues(2.dp),
                    columns = GridCells.Fixed(2)
                ) {
                    items(movies.size) {
                        val movie = movies[it]
                        MovieItem(movie, viewModel, navController)

                    }
                }
            }

        }

    }
}