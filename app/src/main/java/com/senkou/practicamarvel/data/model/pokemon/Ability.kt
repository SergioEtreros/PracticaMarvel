package com.senkou.practicamarvel.data.model.pokemon


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ability(
    val ability: AbilityDetail,
    @SerialName("is_hidden")
    val isHidden: Boolean,
    val slot: Int
)