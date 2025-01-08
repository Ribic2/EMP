package com.example.emp_vid_matej.apiService.data.services

import com.example.emp_vid_matej.apiService.data.response.Token
import com.example.emp_vid_matej.apiService.data.reqeuest.LoginRequest
import com.example.emp_vid_matej.apiService.data.reqeuest.RegisterRequest
import com.example.emp_vid_matej.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<Token>

    @POST("register")
    suspend fun register(@Body request: RegisterRequest): Response<Token>

    @POST("logout")
    suspend fun logout(@Body request: LoginRequest): Response<Token>

    @POST("me")
    suspend fun me(): Response<User>
}