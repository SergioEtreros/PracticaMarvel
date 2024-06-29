package com.senkou.practicamarvel

import android.app.Application
import androidx.room.Room
import com.senkou.practicamarvel.framework.database.room.model.database.MarvelDB

class PracticaMarvelApp : Application() {

  lateinit var marvelDB: MarvelDB
    private set

  override fun onCreate() {
    super.onCreate()
    marvelDB = Room.databaseBuilder(this, MarvelDB::class.java, "marvel")
      .build()
  }
}