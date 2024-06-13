package com.senkou.practicamarvel.data.network.marvel.model

import kotlinx.serialization.Serializable

@Serializable
data class ComicData(
  val offset: Int,
  val limit: Int,
  val total: Int,
  val count: Int,
  val results: List<RemoteComic>
)