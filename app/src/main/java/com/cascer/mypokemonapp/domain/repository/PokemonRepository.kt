package com.cascer.mypokemonapp.domain.repository

import androidx.paging.PagingData
import com.cascer.mypokemonapp.domain.model.Pokemon
import com.cascer.mypokemonapp.domain.model.PokemonDetail
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(): Flow<PagingData<Pokemon>>
    fun searchPokemon(query: String): Flow<PagingData<Pokemon>>
    suspend fun getPokemonDetail(id: Int): PokemonDetail?
}