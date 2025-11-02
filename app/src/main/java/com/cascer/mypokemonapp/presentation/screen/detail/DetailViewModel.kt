package com.cascer.mypokemonapp.presentation.screen.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cascer.mypokemonapp.domain.model.PokemonDetail
import com.cascer.mypokemonapp.domain.usecase.GetPokemonDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class DetailUiState {
    object Idle : DetailUiState()
    object Loading : DetailUiState()
    data class Success(val data: PokemonDetail) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: GetPokemonDetailUseCase
) : ViewModel() {

    var uiState by mutableStateOf<DetailUiState>(DetailUiState.Idle)
        private set

    fun getDetail(id: Int) = viewModelScope.launch {
        uiState = DetailUiState.Loading
        runCatching { useCase.invoke(id) }
            .onSuccess {
                it?.let { uiState = DetailUiState.Success(it) } ?: run {
                    uiState = DetailUiState.Error("Not found")
                }
            }
            .onFailure {
                uiState = DetailUiState.Error(it.message ?: "Oops! Something went wrong. Please try again.")
            }
    }
}