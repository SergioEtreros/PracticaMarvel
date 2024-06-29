package com.senkou.practicamarvel.data.datasource

import com.senkou.practicamarvel.domain.model.Character
import com.senkou.practicamarvel.domain.model.Comic

interface CharactersRemoteDataSource {
  suspend fun getCharacters(): List<Character>
  suspend fun getCharacterById(id: Int): Character
  suspend fun getComicsByCharacterId(id: Int): List<Comic>
}