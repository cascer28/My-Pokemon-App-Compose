package com.cascer.mypokemonapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.cascer.mypokemonapp.data.mapper.toDomain
import com.cascer.mypokemonapp.data.mapper.toEntity
import com.cascer.mypokemonapp.data.source.local.PokemonDatabase
import com.cascer.mypokemonapp.data.source.remote.api.ApiService
import com.cascer.mypokemonapp.data.source.remote.mediator.PokemonMediator
import com.cascer.mypokemonapp.domain.model.Pokemon
import com.cascer.mypokemonapp.domain.model.PokemonDetail
import com.cascer.mypokemonapp.domain.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val database: PokemonDatabase
) : PokemonRepository {

    companion object {
        private const val LIMIT_SIZE = 10
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getPokemonList(): Flow<PagingData<Pokemon>> = Pager(
        config = PagingConfig(LIMIT_SIZE),
        remoteMediator = PokemonMediator(apiService, database),
        pagingSourceFactory = { database.pokemonDao().getPokemonList() }
    ).flow.map { it.map { data -> data.toDomain() } }

    override fun searchPokemon(query: String): Flow<PagingData<Pokemon>> = Pager(
        config = PagingConfig(LIMIT_SIZE),
        pagingSourceFactory = { database.pokemonDao().searchPokemonList("%$query%") }
    ).flow.map { it.map { data -> data.toDomain() } }

    override suspend fun getPokemonDetail(id: Int): PokemonDetail? = withContext(Dispatchers.IO) {
        val localData = database.pokemonDao().getPokemonDetail(id)
        localData?.toDomain() ?: runCatching {
            val result = apiService.getPokemonDetail(id)
            database.pokemonDao().insertPokemonDetail(result.toEntity())
            result.toDomain()
        }.getOrNull()
    }
}