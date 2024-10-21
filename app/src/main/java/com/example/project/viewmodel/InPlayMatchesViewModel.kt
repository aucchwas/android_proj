package com.example.project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.repo.InPlayMatchesrepo
import com.example.project.viewmodel.State.MatchesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class InPlayMatchesViewModel @Inject constructor(private val inPlayMatchesrepo: InPlayMatchesrepo)
    : ViewModel() {

    private val _inPlayMatchesState = MutableStateFlow<MatchesState>(MatchesState.Empty)
    val inPlayMatchesState: StateFlow<MatchesState> = _inPlayMatchesState

    init {
        getAllInPlayMatches()
    }

    private fun getAllInPlayMatches() {
        _inPlayMatchesState.value = MatchesState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val inPlayMatchesResponse = inPlayMatchesrepo.getAllInPlayMatches()
                _inPlayMatchesState.value = if (inPlayMatchesResponse != null) {
                    MatchesState.Success(inPlayMatchesResponse)
                } else {
                    MatchesState.Error("No matches found")
                }
            }
            catch (exception: HttpException){
                _inPlayMatchesState.value = MatchesState.Error("No internet")
            }
            catch (exception: IOException){
                _inPlayMatchesState.value = MatchesState.Error("There is something we should check")
            }
        }
    }
}