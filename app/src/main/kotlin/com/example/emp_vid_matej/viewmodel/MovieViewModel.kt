package com.example.emp_vid_matej.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emp_vid_matej.apiService.data.response.MoviesResponse
import com.example.emp_vid_matej.apiService.data.response.Token
import com.example.emp_vid_matej.model.Movie
import com.example.emp_vid_matej.repository.AuthRepository
import com.example.emp_vid_matej.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _movieResults = MutableLiveData<MoviesResponse>()
    val movieResults: LiveData<MoviesResponse?> = _movieResults

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _movieResultsById = MutableLiveData<Movie>()
    val movieResultsById: LiveData<Movie> = _movieResultsById

    fun getMovies(){
        _isLoading.value = true;

        viewModelScope.launch {
            try {
                val response = movieRepository.getMovies()
                _movieResults.value = response
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getMovieById(id: Int){
        _isLoading.value = true;

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
}