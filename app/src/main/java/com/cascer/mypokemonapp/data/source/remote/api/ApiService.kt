package com.cascer.mypokemonapp.data.source.remote.api

import com.cascer.mypokemonapp.data.source.remote.model.PokemonDetailResponse
import com.cascer.mypokemonapp.data.source.remote.model.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonListResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(
        @Path("id") id: Int
    ): PokemonDetailResponse
}