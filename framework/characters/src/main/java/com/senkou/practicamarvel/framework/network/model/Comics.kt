package com.senkou.practicamarvel.framework.network.model

import kotlinx.serialization.Serializable


@Serializable
data class Comics(
  val available: Int,
  val collectionURI: String,
  val items: List<Comic>,
  val returned: Int
)