package com.senkou.practicamarvel.data.network.pokemon.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemotePokemon(
    val count: Int,
    val next: String,
    val previous: String?,
    @SerialName("results") val pokemonList: List<Pokemon>
)