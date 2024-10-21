package com.example.project.utils

import okhttp3.Interceptor
import okhttp3.Response

class RequestIntercepter: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("x-rapidapi-key", APIKEY)
            .addHeader("x-rapidapi-host", "v3.football.api-sports.io")
            .build()

        return chain.proceed(request)
    }
}