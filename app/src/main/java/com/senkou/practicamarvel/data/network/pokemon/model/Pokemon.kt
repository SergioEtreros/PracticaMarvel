package com.senkou.practicamarvel.data.network.pokemon.model


import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val name: String,
    val url: String
)