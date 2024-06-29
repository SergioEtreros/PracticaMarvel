package com.senkou.practicamarvel.framework.remote.marvel.model


import kotlinx.serialization.Serializable

@Serializable
data class Comic(
    val resourceURI: String,
    val name: String
)