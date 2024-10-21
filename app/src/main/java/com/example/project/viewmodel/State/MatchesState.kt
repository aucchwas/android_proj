package com.example.project.viewmodel.State

import com.example.project.data.remote.models.Match

sealed class MatchesState {
    object Empty : MatchesState()
    object Loading : MatchesState()
    class Success(val data: List<Match>) : MatchesState()
    class Error(val error: String) : MatchesState()
}