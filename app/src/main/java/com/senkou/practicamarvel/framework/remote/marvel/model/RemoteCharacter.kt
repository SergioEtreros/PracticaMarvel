package com.senkou.practicamarvel.framework.remote.marvel.model

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