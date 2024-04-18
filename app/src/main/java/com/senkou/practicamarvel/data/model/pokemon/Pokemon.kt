package com.senkou.practicamarvel.data.model.pokemon


import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val name: String,
    val url: String
)