package com.cascer.mypokemonapp.domain.usecase

import com.cascer.mypokemonapp.domain.model.PokemonDetail
import com.cascer.mypokemonapp.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend fun invoke(id: Int): PokemonDetail? = repository.getPokemonDetail(id)
}