package com.senkou.practicamarvel.framework.database.room.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.senkou.practicamarvel.framework.database.room.model.entities.Character
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

  @Query("SELECT COUNT(id) FROM character")
  suspend fun count(): Int

  @Query("SELECT * FROM character")
  fun getCharacters(): Flow<List<Character>>

  @Query("SELECT * FROM character WHERE id = :id")
  fun getCharacter(id: Int): Flow<Character?>

  @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
  suspend fun insertCharacter(character: Character)

  @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
  suspend fun insertCharacters(characters: List<Character>)

  @Query("DELETE FROM character")
  suspend fun deleteAll()
}