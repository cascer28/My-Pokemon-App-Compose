package com.cascer.mypokemonapp.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_detail")
data class PokemonDetailEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val abilities: List<String>,
    val imageUrl: String
)