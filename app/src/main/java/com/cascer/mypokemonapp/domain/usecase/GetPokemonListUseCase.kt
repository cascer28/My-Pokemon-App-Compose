package com.cascer.mypokemonapp.domain.usecase

import androidx.paging.PagingData
import com.cascer.mypokemonapp.domain.model.Pokemon
import com.cascer.mypokemonapp.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    fun invoke(): Flow<PagingData<Pokemon>> = repository.getPokemonList()
}