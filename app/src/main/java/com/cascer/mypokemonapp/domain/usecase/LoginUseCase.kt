package com.cascer.mypokemonapp.domain.usecase

import com.cascer.mypokemonapp.domain.model.User
import com.cascer.mypokemonapp.domain.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend fun invoke(username: String, password: String): User? =
        repository.login(username = username, password = password)
}