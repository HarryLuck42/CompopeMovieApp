package com.example.movieapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Language(
    @SerializedName("iso_639_1")
    var code : String? = "",
    @SerializedName("name")
    var name : String? = ""
): Serializable
