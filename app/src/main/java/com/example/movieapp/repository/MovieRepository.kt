package com.example.movieapp.repository

import com.example.movieapp.data.MovieDao
import com.example.movieapp.model.Movie
import com.example.movieapp.model.response.*
import com.example.movieapp.network.MovieApi
import com.example.movieapp.utils.DataOrException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepository @Inject constructor(private val api: MovieApi, private val dao: MovieDao) {

    //Api Service
    suspend fun getListMovies(
        movie: String, page: String, language: String)
            : DataOrException<MovieResponse, Boolean, Exception> {
        val response = try {
            api.getListMovies(movie, page, language)

        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response)

    }

    suspend fun getMovieDetail(id: String, language: String)
            : DataOrException<MovieDetailResponse, Boolean, Exception> {
        val response = try {
            api.getMovieDetail(id, language)

        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response)

    }

    suspend fun getMovieReview(id: String, page: String, language: String)
            : DataOrException<MovieReviewResponse, Boolean, Exception> {
        val response = try {
            api.getMovieReview(id, page, language)

        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response)

    }

    suspend fun getVideos(id: String, language: String)
            : DataOrException<VideoResponse, Boolean, Exception> {
        val response = try {
            api.getDataVideo(id, language)

        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }

    suspend fun searchMovieByKeyWord(id: String, language: String, keyword: String, includeAdult: String)
            : DataOrException<MovieResponse, Boolean, Exception> {
        val response = try {
            api.searchMovieByKeyWord(id, language, keyword, includeAdult)

        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response)

    }

    //Database Room

    fun getFavorites(keyword: String): Flow<MutableList<Movie>?>{
        return if(keyword.isNotEmpty()){
            dao.searchFavorite("%$keyword%")
        }else{
            dao.getFavorites()
        }
    }

    suspend fun insertFavorite(movie: Movie) = dao.insertFavorite(movie)

    suspend fun deleteFavorite(movie: Movie) = dao.deleteFavorite(movie)
}