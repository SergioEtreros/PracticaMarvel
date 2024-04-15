package com.senkou.practicamarvel.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Thumbnail(
  val path: String,
  val extension: String
)