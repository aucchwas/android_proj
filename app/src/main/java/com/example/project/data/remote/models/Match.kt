package com.example.project.data.remote.models

data class Match(
    val leagueName: String,
    val homeID: Int,
    val homeName: String,
    val awayID: Int,
    val awayName: String,
    val date: String,
    val status: String,
    val homeScore: Int,
    val awayScore: Int,
    val elapsed: Int
)
