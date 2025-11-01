package com.cascer.mypokemonapp.presentation.screen.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cascer.mypokemonapp.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val useCase: RegisterUseCase
): ViewModel() {

    var registerSuccess by mutableStateOf(false)
        private set
    var error by mutableStateOf("")
        private set

    fun register(name: String, username: String, password: String, confirmPassword: String) {
        if (name.isBlank() || username.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            error = "Oops! Some fields are empty. Please complete all fields."
            return
        }

        if (password != confirmPassword) {
            error = "Oops! Your passwords don’t match."
            return
        }
        viewModelScope.launch {
            runCatching { useCase.invoke(name = name, username = username, password = password) }
                .onSuccess {
                    registerSuccess = true
                }
                .onFailure { error = it.message ?: "Oops! Registration failed. Please try again." }
        }
    }

    fun clearError() {
        error = ""
    }
}