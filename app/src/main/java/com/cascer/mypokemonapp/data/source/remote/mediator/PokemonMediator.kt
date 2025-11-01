package com.cascer.mypokemonapp.data.source.remote.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.cascer.mypokemonapp.data.mapper.toEntity
import com.cascer.mypokemonapp.data.source.local.PokemonDatabase
import com.cascer.mypokemonapp.data.source.local.dao.PokemonRemoteKeysDao
import com.cascer.mypokemonapp.data.source.local.entity.PokemonListEntity
import com.cascer.mypokemonapp.data.source.local.entity.PokemonRemoteKeysEntity
import com.cascer.mypokemonapp.data.source.remote.api.ApiService

@OptIn(ExperimentalPagingApi::class)
class PokemonMediator(
    private val apiService: ApiService,
    private val database: PokemonDatabase
) : RemoteMediator<Int, PokemonListEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonListEntity>
    ): MediatorResult {
        val pokemonDao = database.pokemonDao()
        val remoteKeysDao = database.pokemonRemoteKeysDao()

        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
//                    val remoteKeys = getRemoteKeyForCurrentPosition(state, remoteKeysDao)
//                    remoteKeys?.nextKey?.minus(1) ?: 1
                    1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state, remoteKeysDao)
                    val prevKey = remoteKeys?.prevKey ?: return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                    prevKey
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state, remoteKeysDao)
                    val nextKey = remoteKeys?.nextKey ?: return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                    nextKey
                }
            }

            val response = apiService.getPokemonList(
                offset = (page - 1) * state.config.pageSize,
                limit = state.config.pageSize
            )

            val endOfPaginationReached = response.next == null

            val entities = response.results?.map { it.toEntity() }.orEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeysDao.deleteAll()
                    pokemonDao.deletePokemonList()
                }

                val keys = entities.map {
                    PokemonRemoteKeysEntity(
                        id = it.id,
                        prevKey = if (page == 0) null else page - 1,
                        nextKey = if (endOfPaginationReached) null else page + 1
                    )
                }

                remoteKeysDao.insertAll(keys)
                pokemonDao.insertPokemonList(entities)
            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }


    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, PokemonListEntity>,
        dao: PokemonRemoteKeysDao
    ): PokemonRemoteKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { dao.getRemoteKeys(it.id) }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, PokemonListEntity>,
        dao: PokemonRemoteKeysDao
    ): PokemonRemoteKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { dao.getRemoteKeys(it.id) }
    }

    private suspend fun getRemoteKeyForCurrentPosition(
        state: PagingState<Int, PokemonListEntity>,
        dao: PokemonRemoteKeysDao
    ): PokemonRemoteKeysEntity? =
        state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                dao.getRemoteKeys(id)
            }
        }
}