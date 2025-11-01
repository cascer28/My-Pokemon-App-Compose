package com.cascer.mypokemonapp.domain.usecase

import androidx.paging.PagingData
import com.cascer.mypokemonapp.domain.model.Pokemon
import com.cascer.mypokemonapp.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchPokemonUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    fun invoke(query: String): Flow<PagingData<Pokemon>> = repository.searchPokemon(query = query)
}