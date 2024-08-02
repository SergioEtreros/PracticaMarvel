package com.senkou.practicamarvel.ui.common

import kotlinx.serialization.Serializable

@Serializable
object Splash

@Serializable
object Home

@Serializable
data class Detail(val characterId: Int)