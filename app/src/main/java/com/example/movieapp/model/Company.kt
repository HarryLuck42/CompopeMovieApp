package com.example.movieapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Company(
    @SerializedName("id")
    var id : Long? = 0,
    @SerializedName("logo_path")
    var logoPath : String? = "",
    @SerializedName("name")
    var name : String? = "",
    @SerializedName("origin_country")
    var originCountry : String? = ""
): Serializable
