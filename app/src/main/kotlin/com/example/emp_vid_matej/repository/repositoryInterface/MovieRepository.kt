package com.example.emp_vid_matej.repository.repositoryInterface

import android.content.Context
import com.example.emp_vid_matej.model.Movie

interface MovieRepository {
    fun getMovie(context: Context): Movie;
    fun getMovies(context: Context): List<Movie>;
}