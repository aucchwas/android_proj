package com.example.project.data.remote

import com.example.project.data.remote.models.InPlayMatchesResponse
import com.example.project.utils.GET_IN_PLAY
import retrofit2.http.GET

interface ApiFootball {
    @GET(GET_IN_PLAY)
    suspend fun getInPlayMatches(): InPlayMatchesResponse
}