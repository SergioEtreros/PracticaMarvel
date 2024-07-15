package com.senkou.practicamarvel.framework.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Thumbnail(
  val path: String,
  val extension: String
)