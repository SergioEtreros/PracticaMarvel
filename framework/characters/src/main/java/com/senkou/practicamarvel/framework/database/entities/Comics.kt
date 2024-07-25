package com.senkou.practicamarvel.framework.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
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
  indices = [Index("characterId")]
)
data class Comics(
  @PrimaryKey(autoGenerate = false)
  val id: Int,
  val characterId: Int,
  val imgUrl: String,
)
