package com.example.movieapp.data

import androidx.room.*
import com.example.movieapp.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * from favorites")
    fun getFavorites(): Flow<MutableList<Movie>?>

    @Query("SELECT * from favorites where title like :title")
    fun searchFavorite(title: String): Flow<MutableList<Movie>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Movie)

    @Delete
    suspend fun deleteFavorite(favorite: Movie)
}