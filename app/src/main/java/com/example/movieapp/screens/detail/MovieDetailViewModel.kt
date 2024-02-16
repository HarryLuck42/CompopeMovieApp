package com.example.movieapp.screens.detail

import androidx.lifecycle.ViewModel
import com.example.movieapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val repo: MovieRepository) : ViewModel(){

}