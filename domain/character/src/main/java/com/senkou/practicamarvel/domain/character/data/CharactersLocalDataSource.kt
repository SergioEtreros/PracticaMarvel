package com.senkou.practicamarvel.domain.character.data

import com.senkou.practicamarvel.domain.character.entities.Character
import com.senkou.practicamarvel.domain.character.entities.Comic
import kotlinx.coroutines.flow.Flow

interface CharactersLocalDataSource {
  val characters: Flow<List<Character>>
  fun getCharacter(id: Int): Flow<Character?>
  suspend fun saveCharacter(character: Character)
  suspend fun saveAllCharacters(characters: List<Character>)
  fun getComicsByCharacterId(id: Int): Flow<List<String>>
  suspend fun saveComics(comics: List<Comic>)
  suspend fun deleteAll()
}