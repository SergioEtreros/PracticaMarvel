package com.senkou.practicamarvel.data

import com.senkou.practicamarvel.domain.data.Character

class CharactersRepository {

  suspend fun fetchCharacters(): List<Character> =
    CharactersClient
      .instance
      .getCharacters()
      .data
      .results
      .map {
        it.toDomainModel()
      }



  suspend fun fetchCharacterById(id: Int): Character =
    CharactersClient
      .instance
      .getCharacterById(id)
      .data
      .results
      .first()
      .toDomainModel()

}

private fun RemoteCharacter.toDomainModel(): Character =
  Character(
    id = id,
    name = name,
    description = description,
    imageUrl = "${thumbnail.path}.${thumbnail.extension}"
  )