package com.cascer.mypokemonapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cascer.mypokemonapp.data.source.local.entity.PokemonRemoteKeysEntity

@Dao
interface PokemonRemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKeys: List<PokemonRemoteKeysEntity>)

    @Query("SELECT * FROM pokemon_remote_keys WHERE id = :id")
    suspend fun getRemoteKeys(id: Int): PokemonRemoteKeysEntity?

    @Query("DELETE FROM pokemon_remote_keys")
    suspend fun deleteAll()
}