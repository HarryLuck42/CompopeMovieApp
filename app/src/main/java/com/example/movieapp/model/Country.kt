package com.example.movieapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Country(
    @SerializedName("iso_3166_1")
    var code : String? = "",
    @SerializedName("name")
    var name : String? = ""
): Serializable