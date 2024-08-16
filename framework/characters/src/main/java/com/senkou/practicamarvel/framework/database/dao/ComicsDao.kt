package com.senkou.practicamarvel.framework.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.senkou.practicamarvel.framework.database.entities.Comics

@Dao
interface ComicsDao {

   @Query("SELECT * FROM comics WHERE characterId = :characterId")
   suspend fun getComicsByCharacterId(characterId: Int): List<Comics>

   @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
   suspend fun insertComics(comics: List<Comics>)
}