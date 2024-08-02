package com.senkou.practicamarvel.domain.character.entities

data class Character(
  val id: Int,
  val name: String,
  val description: String,
  val imageUrl: String,
  val favorite: Boolean
)
