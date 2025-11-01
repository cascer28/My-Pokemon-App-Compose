package com.cascer.mypokemonapp.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("abilities")
    val abilities: List<Abilities>?,
    @SerializedName("sprites")
    val sprites: Sprites?
)

data class Abilities(
    @SerializedName("ability")
    val ability: Ability?
)

data class Ability(
    @SerializedName("name")
    val name: String?
)

data class Sprites(
    @SerializedName("other")
    val other: Other?
)

data class Other(
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork?
)

data class OfficialArtwork(
    @SerializedName("front_default")
    val frontDefault: String?
)
