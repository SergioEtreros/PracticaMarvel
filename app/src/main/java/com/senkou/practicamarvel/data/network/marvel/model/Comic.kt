package com.senkou.practicamarvel.data.network.marvel.model


import kotlinx.serialization.Serializable

@Serializable
data class Comic(
    val resourceURI: String,
    val name: String
)