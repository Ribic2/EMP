package com.example.emp_vid_matej.repository

import android.util.Log
import com.example.emp_vid_matej.apiService.data.reqeuest.MovieCommentRequest
import com.example.emp_vid_matej.apiService.data.reqeuest.MovieFilter
import com.example.emp_vid_matej.apiService.data.response.CommentResponse
import com.example.emp_vid_matej.apiService.data.response.MoviesResponse
import com.example.emp_vid_matej.apiService.data.services.MovieApiService
import com.example.emp_vid_matej.model.LikeStatus
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

    suspend fun likeMovie(id: Int): LikeStatus {
        val response = movieApiService.likeMovie(id);

        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Empty response body")
        } else {
            throw Exception(response.errorBody()?.string() ?: "Unknown error")
        }
    }

    suspend fun addComment(movieCommentRequest: MovieCommentRequest): CommentResponse {
        val response = movieApiService.addComment(movieCommentRequest);

        Log.d("comment", movieCommentRequest.toString());
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Empty response body")
        } else {
            throw Exception(response.errorBody()?.string() ?: "Unknown error")
        }
    }
}