package com.example.emp_vid_matej.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emp_vid_matej.apiService.data.reqeuest.RegisterRequest
import com.example.emp_vid_matej.apiService.data.response.Token
import com.example.emp_vid_matej.model.User
import com.example.emp_vid_matej.repository.AuthRepository
import com.example.emp_vid_matej.service.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val sessionManager: SessionManager
) : ViewModel() {


    private val _loginResult = MutableLiveData<Token?>()
    private val _registerResult = MutableLiveData<Token?>()
    private val _userResult = MutableLiveData<User>()

    val loginResult: LiveData<Token?> = _loginResult
    val registerResult: LiveData<Token?> = _registerResult
    val userResult: LiveData<User> = _userResult

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun login(email: String, password: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = authRepository.login(email, password)
                _loginResult.value = response
                sessionManager.saveAuthToken(response.token)
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun logout(){
        sessionManager.removeToken()
    }

    fun user(){
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = authRepository.user()
                _userResult.value = response
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun register(request: RegisterRequest) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = authRepository.register(request)
                _registerResult.value = response
                sessionManager.saveAuthToken(response.token)
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
    fun clearError() {
        _error.value = null
    }

}