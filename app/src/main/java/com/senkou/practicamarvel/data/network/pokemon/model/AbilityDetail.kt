package com.senkou.practicamarvel.data.network.pokemon.model


import kotlinx.serialization.Serializable

@Serializable
data class AbilityDetail(
    val name: String,
    val url: String
)