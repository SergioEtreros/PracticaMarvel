package com.senkou.practicamarvel.data.model.marvel

import kotlinx.serialization.Serializable

@Serializable
data class Thumbnail(
  val path: String,
  val extension: String
)