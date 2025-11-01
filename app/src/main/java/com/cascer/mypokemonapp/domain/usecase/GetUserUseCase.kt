package com.cascer.mypokemonapp.domain.usecase

import com.cascer.mypokemonapp.domain.model.User
import com.cascer.mypokemonapp.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend fun invoke(username: String): User? = repository.getUser(username = username)
}