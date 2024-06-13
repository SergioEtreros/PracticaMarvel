package com.senkou.practicamarvel.data.local.room.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.senkou.practicamarvel.data.local.room.model.dao.CharacterDao
import com.senkou.practicamarvel.data.local.room.model.dao.ComicsDao
import com.senkou.practicamarvel.data.local.room.model.entities.Character
import com.senkou.practicamarvel.data.local.room.model.entities.Comics

@Database(
  entities = [
    Character::class,
    Comics::class,
  ],
  version = 1,
  exportSchema = true,
  autoMigrations = []
)
abstract class MarvelDB : RoomDatabase() {

  abstract fun characterDao(): CharacterDao
  abstract fun comicsDao(): ComicsDao
}