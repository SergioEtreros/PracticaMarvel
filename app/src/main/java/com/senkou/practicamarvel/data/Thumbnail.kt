package com.senkou.practicamarvel.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Thumbnail(
    val path: String,
    val extension: String
)