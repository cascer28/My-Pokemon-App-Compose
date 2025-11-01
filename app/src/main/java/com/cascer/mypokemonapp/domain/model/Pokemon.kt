package com.cascer.mypokemonapp.domain.model

data class Pokemon(
    val id: Int,
    val name: String
)

data class PokemonDetail(
    val id: Int,
    val name: String,
    val abilities: List<String>,
    val imageUrl: String
)