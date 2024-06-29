package com.senkou.practicamarvel.data.datasource

import com.senkou.practicamarvel.domain.model.Comic
import com.senkou.practicamarvel.framework.database.room.model.entities.Character
import com.senkou.practicamarvel.framework.database.room.model.entities.Comics
import kotlinx.coroutines.flow.Flow
import com.senkou.practicamarvel.domain.model.Character as DomainCharacter

interface CharactersLocalDataSource {
  val characters: Flow<List<Character>>
  fun getCharacter(id: Int): Flow<Character?>
  suspend fun isEmpty(): Boolean
  suspend fun saveCharacter(character: DomainCharacter)
  suspend fun saveAllCharacters(characters: List<DomainCharacter>)
  fun getComicsByCharacterId(id: Int): Flow<List<Comics>>
  suspend fun saveComics(comics: List<Comic>)
  suspend fun deleteAll()
}