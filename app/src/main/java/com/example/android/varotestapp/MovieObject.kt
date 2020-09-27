package com.example.android.varotestapp

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieObject {
    private val api: ApiInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ApiInterface::class.java)
    }

    fun getNowPlayingMovies(
        page: Int = 1,
        onSuccess: (movies: List<Movie>) -> Unit,
        onError: () -> Unit
    ) {
        api.getNowPlaying(page = page)
            .enqueue(object : Callback<GetMovies> {
                override fun onResponse(call: Call<GetMovies>, response: Response<GetMovies>) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.movies)
                        } else {
                            onError.invoke()
                        }
                    }
                }

                override fun onFailure(call: Call<GetMovies>, t: Throwable) {
                    onError.invoke()
                }
            }

            )
    }

}