package com.cascer.mypokemonapp.core.di

import android.content.Context
import androidx.room.Room
import com.cascer.mypokemonapp.data.source.local.PokemonDatabase
import com.cascer.mypokemonapp.data.source.local.dao.PokemonDao
import com.cascer.mypokemonapp.data.source.local.dao.PokemonRemoteKeysDao
import com.cascer.mypokemonapp.data.source.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PokemonDatabase {
        return Room.databaseBuilder(
            context,
            PokemonDatabase::class.java,
            "pokemon_database"
        ).build()
    }

    @Provides
    fun providePokemonDao(database: PokemonDatabase): PokemonDao {
        return database.pokemonDao()
    }

    @Provides
    fun providePokemonRemoteKeysDao(database: PokemonDatabase): PokemonRemoteKeysDao {
        return database.pokemonRemoteKeysDao()
    }

    @Provides
    fun provideUserDao(database: PokemonDatabase): UserDao {
        return database.userDao()
    }
}