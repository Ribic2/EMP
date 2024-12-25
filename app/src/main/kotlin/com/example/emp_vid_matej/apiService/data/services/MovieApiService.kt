package com.example.emp_vid_matej.apiService

import com.example.emp_vid_matej.model.Movie
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MovieApiService {

    // Get all movies
    @GET("/movie")
    suspend fun getMovies(): Response<List<Movie>>

    // Get a single movie by ID
    @GET("/movie/{id}")
    suspend fun getMovieById(@Path("id") id: Int): Response<Movie>

    // Like a movie by ID
    @POST("/movie/{id}/like")
    suspend fun likeMovie(@Path("id") id: Int): Response<Void>

    // Get filter data
    @GET("/filter")
    suspend fun getFilterData(): Response<Void>

    // Mark a movie as favourite
    @POST("/movie/{id}/favourite")
    suspend fun favouriteMovie(@Path("id") id: Int): Response<Void>

    // Add a comment to a movie
    @POST("/comment/add")
    suspend fun addComment(): Response<Void>
}