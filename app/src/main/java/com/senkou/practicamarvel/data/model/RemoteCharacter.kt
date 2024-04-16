package com.senkou.practicamarvel.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoteCharacter(
  val id: Int,
  val name: String,
  val description: String,
  val modified: String,
  val thumbnail: Thumbnail,
  val resourceURI: String,
  val comics: Comics,
  val urls: List<Url>
)