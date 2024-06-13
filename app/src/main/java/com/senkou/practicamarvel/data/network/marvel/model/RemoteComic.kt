package com.senkou.practicamarvel.data.network.marvel.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoteComic(
  val id: Int,
  val digitalId: Int,
  val title: String,
//    val issueNumber: Int,
//    val variantDescription: String,
  val description: String?,
//    val modified: String,
//    val isbn: String,
//    val upc: String,
//    val diamondCode: String,
//    val ean: String,
//    val issn: String,
//    val format: String,
//    val pageCount: Int,
//    val textObjects: List<Any>,
//    val resourceURI: String,
//    val urls: List<Url>,
//    val series: Series,
//    val variants: List<Variant>,
//    val collections: List<Any>,
//    val collectedIssues: List<Any>,
//    val dates: List<Date>,
//    val prices: List<Price>,
  val thumbnail: Thumbnail,
//    val images: List<Image>,
//    val creators: Creators,
//    val characters: Characters,
//    val stories: Stories,
//    val events: Events
)