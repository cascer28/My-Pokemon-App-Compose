package com.cascer.mypokemonapp.data.mapper

import com.cascer.mypokemonapp.data.source.local.entity.PokemonDetailEntity
import com.cascer.mypokemonapp.data.source.local.entity.PokemonListEntity
import com.cascer.mypokemonapp.data.source.local.entity.UserEntity
import com.cascer.mypokemonapp.data.source.remote.model.PokemonDetailResponse
import com.cascer.mypokemonapp.data.source.remote.model.PokemonItemResponse
import com.cascer.mypokemonapp.domain.model.Pokemon
import com.cascer.mypokemonapp.domain.model.PokemonDetail
import com.cascer.mypokemonapp.domain.model.User

fun UserEntity.toDomain() = User(id = id, name = name, username = username)

fun PokemonListEntity.toDomain() = Pokemon(id = id, name = name)

fun PokemonDetailEntity.toDomain() = PokemonDetail(
    id = id, name = name, abilities = abilities, imageUrl = imageUrl
)

fun PokemonItemResponse.toEntity() = PokemonListEntity(
    id = url?.trimEnd('/')?.substringAfterLast('/')?.toInt() ?: 0, name = name.orEmpty()
)

fun PokemonDetailResponse.toEntity() = PokemonDetailEntity(
    id = id ?: 0,
    name = name.orEmpty(),
    abilities = abilities?.map { it.ability?.name.orEmpty() } ?: listOf(),
    imageUrl = sprites?.other?.officialArtwork?.frontDefault .orEmpty()
)

fun PokemonItemResponse.toDomain() = Pokemon(
    id = url?.trimEnd('/')?.substringAfterLast('/')?.toInt() ?: 0, name = name.orEmpty()
)

fun PokemonDetailResponse.toDomain() = PokemonDetail(
    id = id ?: 0,
    name = name.orEmpty(),
    abilities = abilities?.map { it.ability?.name.orEmpty() } ?: listOf(),
    imageUrl = sprites?.other?.officialArtwork?.frontDefault .orEmpty()
)