package com.cascer.mypokemonapp.domain.usecase

import com.cascer.mypokemonapp.domain.repository.UserRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend fun invoke(name: String, username: String, password: String) {
        val localUser = repository.getUser(username = username)
        if (localUser != null) {
            throw IllegalArgumentException("Oops! That username’s already taken, Try another one!")
        }
        repository.register(name, username, password)
    }
}