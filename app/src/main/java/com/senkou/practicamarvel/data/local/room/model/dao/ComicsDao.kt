package com.senkou.practicamarvel.data.local.room.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.senkou.practicamarvel.data.local.room.model.entities.Comics
import kotlinx.coroutines.flow.Flow

@Dao
interface ComicsDao {

  @Query("SELECT * FROM comics WHERE characterId = :characterId")
  fun getComicsByCharacterId(characterId: Int): Flow<List<Comics>>

  @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
  suspend fun insertComics(comics: List<Comics>)
}