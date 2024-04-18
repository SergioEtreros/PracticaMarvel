package com.senkou.practicamarvel.data.model.pokemon


import kotlinx.serialization.Serializable

@Serializable
data class AbilityDetail(
    val name: String,
    val url: String
)