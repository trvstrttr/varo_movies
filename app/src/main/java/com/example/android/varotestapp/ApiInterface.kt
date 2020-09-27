package com.example.android.varotestapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("movie/now_playing")
    fun getNowPlaying(
        @Query("api_key") apiKey: String = "7bfe007798875393b05c5aa1ba26323e",
        @Query("page") page: Int
    ): Call<GetMovies>
}