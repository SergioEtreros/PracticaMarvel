package com.senkou.practicamarvel.data


import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<RemoteCharacter>
)