package com.cascer.mypokemonapp.domain.usecase

import com.cascer.mypokemonapp.domain.repository.UserRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend fun invoke() = repository.logout()
}