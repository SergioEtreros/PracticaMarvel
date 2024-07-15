package com.senkou.practicamarvel.framework.network.model


import kotlinx.serialization.Serializable

@Serializable
data class Comic(
    val resourceURI: String,
    val name: String
)