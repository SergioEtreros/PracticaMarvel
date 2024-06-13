package com.senkou.practicamarvel.data.local.room.model.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
  foreignKeys = [
    ForeignKey(
      entity = Character::class,
      parentColumns = ["id"],
      childColumns = ["characterId"],
      onDelete = ForeignKey.CASCADE
    ),
  ],
)
data class Comics(
  @PrimaryKey(autoGenerate = false)
  val id: Int,
  val characterId: Int,
  val imgUrl: String,
)
