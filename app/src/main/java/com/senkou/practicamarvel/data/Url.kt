package com.senkou.practicamarvel.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Url(
    val type: String,
    val url: String
)