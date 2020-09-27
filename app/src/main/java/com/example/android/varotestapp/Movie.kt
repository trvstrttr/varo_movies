package com.example.android.varotestapp

import com.google.gson.annotations.SerializedName

data class Movie (
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("overview") val overview: String
)