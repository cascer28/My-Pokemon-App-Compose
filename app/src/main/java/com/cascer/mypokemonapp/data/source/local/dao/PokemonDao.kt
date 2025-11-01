package com.cascer.mypokemonapp.data.source.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cascer.mypokemonapp.data.source.local.entity.PokemonDetailEntity
import com.cascer.mypokemonapp.data.source.local.entity.PokemonListEntity

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonList(pokemonList: List<PokemonListEntity>)

    @Query("SELECT * FROM pokemon_list ORDER BY id ASC")
    fun getPokemonList(): PagingSource<Int, PokemonListEntity>

    @Query("SELECT * FROM pokemon_list WHERE name LIKE :query")
    fun searchPokemonList(query: String): PagingSource<Int, PokemonListEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonDetail(pokemon: PokemonDetailEntity)

    @Query("SELECT * FROM pokemon_detail WHERE id = :id")
    suspend fun getPokemonDetail(id: Int): PokemonDetailEntity?

    @Query("DELETE FROM pokemon_list")
    suspend fun deletePokemonList()
}