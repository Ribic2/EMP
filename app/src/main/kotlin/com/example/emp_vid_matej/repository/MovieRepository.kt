package com.example.emp_vid_matej.repository

import android.util.Log
import com.example.emp_vid_matej.apiService.data.reqeuest.MovieFilter
import com.example.emp_vid_matej.apiService.data.response.MovieFilterResponse
import com.example.emp_vid_matej.apiService.data.response.MoviesResponse
import com.example.emp_vid_matej.apiService.data.services.MovieApiService
import com.example.emp_vid_matej.model.Movie
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieApiService: MovieApiService
) {

    suspend fun getMovies(movieFilter: MovieFilter): MoviesResponse {
        val response = movieApiService.getMovies(
            movieFilter.q,
            movieFilter.genres
        );

        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Empty response body")
        } else {
            throw Exception(response.errorBody()?.string() ?: "Unknown error")
        }
    }

    suspend fun getMovieById(id: Int): Movie {
        val response = movieApiService.getMovieById(id);

        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Empty response body")
        } else {
            throw Exception(response.errorBody()?.string() ?: "Unknown error")
        }

    }

    suspend fun likeMovie(id: Int): Response<Void>? {
        return null;

    }

    suspend fun getFilterData(): Response<MovieFilterResponse>? {
        return null;

    }

    suspend fun favouriteMovie(id: Int): Response<Void>? {
        return null;

    }

    suspend fun addComment(): Response<Void>? {
        return null;

    }

}