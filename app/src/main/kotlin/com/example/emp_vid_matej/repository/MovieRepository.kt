package com.example.emp_vid_matej.repository

import android.content.Context
import com.example.emp_vid_matej.model.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MovieRepository {
    private lateinit var movies: List<Movie>

    fun getMovie(context: Context): Movie {
        if (!::movies.isInitialized) {
            loadMovies(context)
        }

        // Return the first movie as an example (you might want to improve this logic)
        return movies.firstOrNull() ?: throw IllegalStateException("No movies found")
    }

    fun getMovies(context: Context): List<Movie> {
        if (!::movies.isInitialized) {
            loadMovies(context)
        }
        return movies
    }

    fun getMovieByName(context: Context, name: String): Movie? {
        if (!::movies.isInitialized) {
            loadMovies(context)
        }

        return movies.find { it.Title.equals(name, ignoreCase = true) }
    }

    private fun loadMovies(context: Context) {
        val filePath = "mock.json"

        val jsonString: String =
            context.assets.open(filePath).bufferedReader().use { it.readText() }
        val movieListType = object : TypeToken<List<Movie>>() {}.type
        movies = Gson().fromJson(jsonString, movieListType)
    }

}