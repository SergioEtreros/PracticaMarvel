package com.senkou.practicamarvel.data.network

import com.senkou.practicamarvel.data.model.RemoteCharacter
import com.senkou.practicamarvel.domain.model.Character

class CharactersRepository {

  suspend fun fetchCharacters(): List<Character> =
    CharactersClient.instance
      .getCharacters()
      .data
      .results
      .filter {
        !it.thumbnail.path.contains("image_not_available") &&
            it.thumbnail.extension != "gif"
      }
      .map {
        it.toDomainModel()
      }

  suspend fun fetchCharacterById(id: Int): Character =
    CharactersClient.instance
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
    imageUrl = "${thumbnail.path.replace("http:", "https:")}.${thumbnail.extension}",
    comics = comics.items.map { it.name }
  )