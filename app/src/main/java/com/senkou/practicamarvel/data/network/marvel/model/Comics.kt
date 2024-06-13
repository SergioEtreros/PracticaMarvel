package com.senkou.practicamarvel.data.network.marvel.model


import kotlinx.serialization.Serializable

@Serializable
data class Comics(
  val available: Int,
  val collectionURI: String,
  val items: List<Comic>,
  val returned: Int
)