package com.senkou.practicamarvel.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoteResult(
  val code: Int,
  val status: String,
  val copyright: String,
  val attributionText: String,
  val attributionHTML: String,
  val etag: String,
  val data: Data
)