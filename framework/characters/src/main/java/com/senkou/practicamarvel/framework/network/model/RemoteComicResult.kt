package com.senkou.practicamarvel.framework.network.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoteComicResult(
  val code: Int,
  val status: String,
  val copyright: String,
  val attributionText: String,
  val attributionHTML: String,
  val etag: String,
  val data: ComicData
)