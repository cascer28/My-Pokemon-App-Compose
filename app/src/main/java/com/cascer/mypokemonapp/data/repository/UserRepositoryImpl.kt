package com.cascer.mypokemonapp.data.repository

import com.cascer.mypokemonapp.data.mapper.toDomain
import com.cascer.mypokemonapp.data.source.local.AppDataStore
import com.cascer.mypokemonapp.data.source.local.dao.UserDao
import com.cascer.mypokemonapp.data.source.local.entity.UserEntity
import com.cascer.mypokemonapp.domain.model.LoginUser
import com.cascer.mypokemonapp.domain.model.User
import com.cascer.mypokemonapp.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dao: UserDao,
    private val appDataStore: AppDataStore
) : UserRepository {
    override suspend fun register(
        name: String, username: String, password: String
    ) = withContext(Dispatchers.IO) {
        dao.insertUser(UserEntity(name = name, username = username, password = password))
    }

    override suspend fun login(
        username: String,
        password: String
    ): User? = withContext(Dispatchers.IO) {
        val user = dao.getUser(username = username, password = password)
        user?.let {
            appDataStore.login(username = username, isLogin = true)
            it.toDomain()
        }
    }

    override suspend fun getUser(username: String): User? = withContext(Dispatchers.IO) {
        dao.getUserByUsername(username)?.toDomain()
    }

    override suspend fun getLoginUser(): LoginUser {
        return LoginUser(
            isLogin = appDataStore.isLogin.first(),
            username = appDataStore.username.first()
        )
    }

    override suspend fun logout() {
        appDataStore.clearSession()
    }
}