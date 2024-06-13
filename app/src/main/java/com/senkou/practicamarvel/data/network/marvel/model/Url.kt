package com.senkou.practicamarvel.data.network.marvel.model


import kotlinx.serialization.Serializable

@Serializable
data class Url(
    val type: String,
    val url: String
)