package com.senkou.practicamarvel.data.model.marvel


import kotlinx.serialization.Serializable

@Serializable
data class Comic(
    val resourceURI: String,
    val name: String
)