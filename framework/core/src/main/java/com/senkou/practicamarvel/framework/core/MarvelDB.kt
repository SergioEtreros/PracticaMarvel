package com.senkou.practicamarvel.framework.core

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
  entities = [
    com.senkou.practicamarvel.framework.database.entities.Character::class,
    com.senkou.practicamarvel.framework.database.entities.Comics::class,
  ],
  version = 1,
  exportSchema = true,
  autoMigrations = []
)
abstract class MarvelDB : RoomDatabase() {

  abstract fun characterDao(): com.senkou.practicamarvel.framework.database.dao.CharacterDao
  abstract fun comicsDao(): com.senkou.practicamarvel.framework.database.dao.ComicsDao
}