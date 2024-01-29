package com.example.movieapp.model.response

import com.example.movieapp.model.Company
import com.example.movieapp.model.Country
import com.example.movieapp.model.Genre
import com.example.movieapp.model.Language
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieDetailResponse(
    @SerializedName("id")
    var id : Long? = 0,
    @SerializedName("adult")
    var adult : Boolean? = false,
    @SerializedName("backdrop_path")
    var backdropPath : String? = "",
    @SerializedName("budget")
    var budget : Int? = 0,
    @SerializedName("genres")
    var genres: MutableList<Genre>? = mutableListOf(),
    @SerializedName("homepage")
    var homepage : String? = "",
    @SerializedName("original_language")
    var originalLanguage : String? = "",
    @SerializedName("original_title")
    var originalTitle : String? = "",
    @SerializedName("overview")
    var overview : String? = "",
    @SerializedName("popularity")
    var popularity : Double? = 0.0,
    @SerializedName("poster_path")
    var posterPath : String? = "",
    @SerializedName("production_companies")
    var productionCompanies: MutableList<Company>? = mutableListOf(),
    @SerializedName("production_countries")
    var productionCountries: MutableList<Country>? = mutableListOf(),
    @SerializedName("release_date")
    var releaseDate : String? = "",
    @SerializedName("revenue")
    var revenue : Int? = 0,
    @SerializedName("runtime")
    var runtime : Int? = 0,
    @SerializedName("spoken_languages")
    var spokenLanguages: MutableList<Language>? = mutableListOf(),
    @SerializedName("status")
    var status : String? = "",
    @SerializedName("tagline")
    var tagline : String? = "",
    @SerializedName("title")
    var title : String? = "",
    @SerializedName("video")
    var video : Boolean? = false,
    @SerializedName("vote_average")
    var voteAverage : Double? = 0.0,
    @SerializedName("vote_count")
    var voteCount : Int? = 0
): Serializable
