package com.senkou.practicamarvel.domain.character.data

import com.senkou.practicamarvel.domain.character.entities.Character
import com.senkou.practicamarvel.domain.character.entities.Comic

interface CharactersRemoteDataSource {
  suspend fun getCharacters(): List<Character>
  suspend fun getCharacterById(id: Int): Character
  suspend fun getComicsByCharacterId(id: Int): List<Comic>
}