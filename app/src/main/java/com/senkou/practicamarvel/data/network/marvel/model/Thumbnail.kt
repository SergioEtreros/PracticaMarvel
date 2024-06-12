package com.senkou.practicamarvel.data.network.marvel.model

import kotlinx.serialization.Serializable

@Serializable
data class Thumbnail(
  val path: String,
  val extension: String
)