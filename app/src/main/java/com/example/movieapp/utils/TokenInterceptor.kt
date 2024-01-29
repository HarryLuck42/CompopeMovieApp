package com.example.movieapp.utils

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TokenInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest: Request

        newRequest = request.newBuilder()
            .addHeader("Authorization", "Bearer ${Constants.apikey}")
            .build()
        return chain.proceed(newRequest)
    }

}