package com.cascer.mypokemonapp.domain.usecase

import com.cascer.mypokemonapp.domain.model.LoginUser
import com.cascer.mypokemonapp.domain.repository.UserRepository
import javax.inject.Inject

class GetLoginUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend fun invoke(): LoginUser = repository.getLoginUser()
}