package com.senkou.practicamarvel.data.model.marvel


import kotlinx.serialization.Serializable

@Serializable
data class Url(
    val type: String,
    val url: String
)