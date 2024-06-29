package com.senkou.practicamarvel.framework.remote.marvel.model


import kotlinx.serialization.Serializable

@Serializable
data class Url(
    val type: String,
    val url: String
)