package com.example.project.repo

import com.example.project.data.remote.ApiFootball
import com.example.project.data.remote.models.Match
import javax.inject.Inject

class InPlayMatchesrepo @Inject constructor(private val apifootballService: ApiFootball) {
    suspend fun getAllInPlayMatches(): List<Match>
        = apifootballService.getInPlayMatches().data
}