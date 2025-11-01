package com.cascer.mypokemonapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cascer.mypokemonapp.data.source.local.dao.PokemonDao
import com.cascer.mypokemonapp.data.source.local.dao.PokemonRemoteKeysDao
import com.cascer.mypokemonapp.data.source.local.dao.UserDao
import com.cascer.mypokemonapp.data.source.local.entity.PokemonDetailEntity
import com.cascer.mypokemonapp.data.source.local.entity.PokemonListEntity
import com.cascer.mypokemonapp.data.source.local.entity.PokemonRemoteKeysEntity
import com.cascer.mypokemonapp.data.source.local.entity.UserEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [
        PokemonListEntity::class, PokemonDetailEntity::class,
        PokemonRemoteKeysEntity::class, UserEntity::class
    ]
)
@TypeConverters(Converters::class)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun pokemonRemoteKeysDao(): PokemonRemoteKeysDao
    abstract fun userDao(): UserDao
}