package com.example.emp_vid_matej.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emp_vid_matej.apiService.data.reqeuest.MovieCommentRequest
import com.example.emp_vid_matej.apiService.data.reqeuest.MovieFilter
import com.example.emp_vid_matej.apiService.data.response.MoviesResponse
import com.example.emp_vid_matej.model.LikeStatus
import com.example.emp_vid_matej.model.Movie
import com.example.emp_vid_matej.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    // Filters
    private val _currentFilter = MutableStateFlow(MovieFilter())
    val currentFilter: StateFlow<MovieFilter> = _currentFilter

    private val _movieResults = MutableStateFlow(MoviesResponse())
    val movieResults: StateFlow<MoviesResponse> = _movieResults

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _movieResultsById = MutableStateFlow<Movie?>(null)
    val movieResultsById: StateFlow<Movie?> = _movieResultsById

    private val _movieLikeById = MutableStateFlow<LikeStatus?>(null)
    val movieLikeById: StateFlow<LikeStatus?> = _movieLikeById

    private val _movieComment = MutableStateFlow<LikeStatus?>(null)
    val movieComment: StateFlow<LikeStatus?> = _movieComment

    private val _movieResultsRecommended = MutableStateFlow<List<Movie>?>(null);
    val movieResultsRecommended: StateFlow<List<Movie>?> = _movieResultsRecommended

    fun getRecommended() {
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val response = movieRepository.getRecommended()

                Log.d("recommendedMovies", response.toString())
                _movieResultsRecommended.value = response
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getMovies(movieFilter: MovieFilter) {
        _currentFilter.value = movieFilter
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val response = movieRepository.getMovies(movieFilter)
                _movieResults.value = response
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun likeMovies(id: Int) {
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val response = movieRepository.likeMovie(id)
                _movieLikeById.value = response
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getMovieById(id: Int) {
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val response = movieRepository.getMovieById(id)
                _movieResultsById.value = response
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun postComment(movieCommentRequest: MovieCommentRequest) {
        _isLoading.value = true

        viewModelScope.launch {
            try {
                movieRepository.addComment(movieCommentRequest)
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}
