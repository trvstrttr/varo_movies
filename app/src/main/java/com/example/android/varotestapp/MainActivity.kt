package com.example.android.varotestapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var nowPlayingMovie: RecyclerView
    private lateinit var nowPlayingMoviesAdapter: MoviePosterCard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nowPlayingMovie = findViewById(R.id.now_playing)
        nowPlayingMovie.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        nowPlayingMoviesAdapter =
            MoviePosterCard(mutableListOf()) { movie -> showMovieDetails(movie) }
        nowPlayingMovie.adapter = nowPlayingMoviesAdapter

        MovieObject.getNowPlayingMovies(
            onSuccess = ::onNowPlayingMoviesFetched,
            onError = ::onError
        )
    }

    private fun onNowPlayingMoviesFetched(movie: List<Movie>) {
        nowPlayingMoviesAdapter.updateMovies(movie)
    }

    private fun showMovieDetails(movie: Movie) {
        val intent = Intent(this, Details::class.java)
        intent.putExtra(MOVIE_BACKDROP, movie.backdropPath)
        intent.putExtra(MOVIE_POSTER, movie.posterPath)
        intent.putExtra(MOVIE_TITLE, movie.title)
        intent.putExtra(MOVIE_RELEASE_DATE, movie.releaseDate)
        intent.putExtra(MOVIE_OVERVIEW, movie.overview)
        startActivity(intent)
    }

    private fun onError() {
        Toast.makeText(this, "Failed to get movies", Toast.LENGTH_SHORT).show()
    }
}