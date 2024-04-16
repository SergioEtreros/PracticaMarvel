package com.senkou.practicamarvel.data.model


import kotlinx.serialization.Serializable

@Serializable
data class Comic(
    val resourceURI: String,
    val name: String
)