package com.example.emp_vid_matej.apiService.data

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()


        return chain.proceed(requestBuilder.build())
    }
}