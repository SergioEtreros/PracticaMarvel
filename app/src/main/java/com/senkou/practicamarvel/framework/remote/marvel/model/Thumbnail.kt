package com.senkou.practicamarvel.framework.remote.marvel.model

import kotlinx.serialization.Serializable

@Serializable
data class Thumbnail(
  val path: String,
  val extension: String
)