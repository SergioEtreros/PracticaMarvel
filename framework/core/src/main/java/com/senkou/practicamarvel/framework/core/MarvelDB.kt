package com.senkou.practicamarvel.framework.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.senkou.practicamarvel.framework.database.dao.CharacterDao
import com.senkou.practicamarvel.framework.database.dao.ComicsDao
import com.senkou.practicamarvel.framework.database.entities.Character
import com.senkou.practicamarvel.framework.database.entities.Comics

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