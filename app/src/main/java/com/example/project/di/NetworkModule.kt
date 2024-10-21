package com.example.project.di

import com.example.project.data.remote.ApiFootball
import com.example.project.utils.APIKEY
import com.example.project.utils.BASE_URL
import com.example.project.utils.RequestIntercepter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun okHttp(): OkHttpClient{
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        //val client = OkHttpClient()

        /*val request = Request.Builder()
            .url("https://api-football-v1.p.rapidapi.com/v3/fixtures")
            .addHeader("x-rapidapi-host", "api-football-v1.p.rapidapi.com")
            .addHeader("x-rapidapi-key", APIKEY) // Replace with your actual API key
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            // Handle the response here
            println(response.body?.string())
        }*/
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)

            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original
                    .newBuilder()
                    .url("https://api-football-v1.p.rapidapi.com/v3/fixtures")
                    .header("x-rapidapi-key", APIKEY)
                    .header("x-rapidapi-host", "v3.football.api-sports.io")

                val request = requestBuilder.build()

                chain.proceed(request)
            }
            .build()
    }

    @Provides
    fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    fun apifootballService(retrofit: Retrofit): ApiFootball {
        return retrofit.create(ApiFootball::class.java)
    }
}