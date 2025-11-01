package com.cascer.mypokemonapp.presentation.screen.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cascer.mypokemonapp.domain.model.User
import com.cascer.mypokemonapp.domain.usecase.GetLoginUserUseCase
import com.cascer.mypokemonapp.domain.usecase.GetUserUseCase
import com.cascer.mypokemonapp.domain.usecase.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userUseCase: GetUserUseCase,
    private val loginUserUseCase: GetLoginUserUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    var user by mutableStateOf<User?>(null)
        private set

    var logoutSuccess by mutableStateOf(false)
        private set

    init {
        getUser()
    }

    private fun getUser() = viewModelScope.launch {
        val localUser = loginUserUseCase.invoke()
        if (!localUser.isLogin) return@launch

        runCatching { userUseCase.invoke(localUser.username) }
            .onSuccess { user = it }
            .onFailure { user = null }
    }

    fun logout() = viewModelScope.launch {
        logoutUseCase.invoke()
        logoutSuccess = true
    }
}