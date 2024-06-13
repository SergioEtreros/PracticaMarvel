package com.senkou.practicamarvel.data.local.room.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Character(
  @PrimaryKey(autoGenerate = true)
  val id: Int,
  val name: String,
  val description: String,
  val imageUrl: String,
  val favorite: Boolean
)
