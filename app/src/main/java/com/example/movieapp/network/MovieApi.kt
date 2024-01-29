package com.example.movieapp.network

import com.example.movieapp.model.response.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/{movie}")
    suspend fun getListMovies(
        @Path("movie") movie: String,
        @Query("page") page: String,
        @Query("language") language: String
    ): MovieResponse

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: String,
        @Query("language") language: String
    ): MovieDetailResponse

    @GET("movie/{id}/reviews")
    suspend fun getMovieReview(
        @Path("id") id: String,
        @Query("page") page: String,
        @Query("language") language: String
    ): MovieReviewResponse


    @GET("movie/{id}/videos")
    suspend fun getDataVideo(
        @Path("id") id: String,
        @Query("language") language: String
    ): VideoResponse

    @GET("search/movie")
    suspend fun searchMovieByKeyWord(
        @Query("page") page: String,
        @Query("language") language: String,
        @Query("query") keyword: String,
        @Query("include_adult") includeAdult: String
    ): MovieResponse
}