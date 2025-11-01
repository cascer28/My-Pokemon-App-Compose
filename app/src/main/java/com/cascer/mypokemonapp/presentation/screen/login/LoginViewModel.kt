package com.cascer.mypokemonapp.presentation.screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cascer.mypokemonapp.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: LoginUseCase
): ViewModel() {
    var loginSuccess by mutableStateOf(false)
        private set
    var error by mutableStateOf("")
        private set

    fun login(username: String, password: String) {
        if (username.isBlank() || password.isBlank()) {
            error = "Oops! Please fill in both username and password."
            return
        }
        viewModelScope.launch {
            val localUser = useCase.invoke(username = username, password = password)
            if (localUser != null) loginSuccess = true
            else error = "Oops! That username or password is incorrect."
        }
    }

    fun clearError() {
        error = ""
    }
}