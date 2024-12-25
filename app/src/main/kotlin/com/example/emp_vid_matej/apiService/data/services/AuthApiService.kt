package com.example.emp_vid_matej.apiService

import com.example.emp_vid_matej.apiService.data.response.Token
import com.example.emp_vid_matej.apiService.data.reqeuest.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<Token>

    @POST("/auth/register")
    suspend fun register(@Body request: LoginRequest): Response<Token>

    @POST("/auth/logout")
    suspend fun logout(@Body request: LoginRequest): Response<Token>

    @POST("/auth/me")
    suspend fun me(@Body request: LoginRequest): Response<Token>
}