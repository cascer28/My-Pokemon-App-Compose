package com.cascer.mypokemonapp.domain.repository

import com.cascer.mypokemonapp.domain.model.LoginUser
import com.cascer.mypokemonapp.domain.model.User

interface UserRepository {
    suspend fun register(name: String, username: String, password: String)
    suspend fun login(username: String, password: String): User?
    suspend fun getUser(username: String): User?
    suspend fun getLoginUser(): LoginUser
    suspend fun logout()
}