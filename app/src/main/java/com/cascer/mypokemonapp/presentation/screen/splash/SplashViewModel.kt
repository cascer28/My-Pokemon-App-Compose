package com.cascer.mypokemonapp.presentation.screen.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cascer.mypokemonapp.domain.usecase.GetLoginUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCase: GetLoginUserUseCase
): ViewModel() {

    var isLogin by mutableStateOf(false)
        private set

    init {
        checkLoginUser()
    }

    private fun checkLoginUser() = viewModelScope.launch {
        val loginUser = useCase.invoke()
        isLogin = loginUser.isLogin
    }
}