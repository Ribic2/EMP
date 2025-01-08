package com.example.emp_vid_matej.repository

import android.util.Log
import com.example.emp_vid_matej.apiService.data.reqeuest.LoginRequest
import com.example.emp_vid_matej.apiService.data.reqeuest.RegisterRequest
import com.example.emp_vid_matej.apiService.data.response.Token
import com.example.emp_vid_matej.apiService.data.services.AuthApiService
import com.example.emp_vid_matej.model.User
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authApiService: AuthApiService,
) {

    suspend fun login(email: String, password: String): Token {
        val request = LoginRequest(email, password)
        val response = authApiService.login(request);

        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Empty response body")
        } else {
            throw Exception(response.errorBody()?.string() ?: "Unknown error")
        }
    }

    suspend fun register(request: RegisterRequest): Token{
        val response = authApiService.register(request);

        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Empty response body")
        } else {
            throw Exception(response.errorBody()?.string() ?: "Unknown error")
        }
    }

    suspend fun user(): User {
        val response = authApiService.me();

        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Empty response body")
        } else {
            throw Exception(response.errorBody()?.string() ?: "Unknown error")
        }
    }
}