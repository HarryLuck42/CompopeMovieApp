package com.example.movieapp.screens.home

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.MenuItem
import com.example.movieapp.model.Movie
import com.example.movieapp.model.response.MovieResponse
import com.example.movieapp.repository.MovieRepository
import com.example.movieapp.utils.Constants
import com.example.movieapp.utils.DataOrException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: MovieRepository) : ViewModel() {

    var openSearch = mutableStateOf(false)
    var keyword = mutableStateOf("")

    private val _favorites = MutableStateFlow<List<Movie>>(emptyList())
    val favorite = _favorites.asStateFlow()

    init {
        viewModelScope.launch {
            repo.getFavorites(keyword.value).distinctUntilChanged().collect{ values ->
                if(values.isNullOrEmpty()){
                    Log.d("TAG", ": Empty favs ")
                }else{
                    _favorites.value = values
                    Log.d("TAG", ": Not Empty favs ")
                }
            }
        }
    }

    fun insertFavorite(value: Movie) = viewModelScope.launch { repo.insertFavorite(value) }

    fun deleteFavorite(value: Movie) = viewModelScope.launch { repo.deleteFavorite(value) }



    suspend fun getAllMovies(type: String, paging: String) : DataOrException<MovieResponse, Boolean, Exception>{
        return repo.getListMovies(
            type,
            paging,
            Constants.language,
        )
    }

    suspend fun searchMovies(paging: String, keyword: String): DataOrException<MovieResponse, Boolean, Exception>{
        return repo.searchMovieByKeyWord(
            paging,
            Constants.language,
            keyword,
            "false"
        )
    }

}