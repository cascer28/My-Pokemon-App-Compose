package com.cascer.mypokemonapp.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_list")
data class PokemonListEntity(
    @PrimaryKey
    val id: Int,
    val name: String
)
