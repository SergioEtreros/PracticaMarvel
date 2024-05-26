package com.senkou.practicamarvel.data.network.marvel

import com.senkou.practicamarvel.data.model.marvel.RemoteCharacter
import com.senkou.practicamarvel.data.model.marvel.Thumbnail
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

  suspend fun getCharacterComics(characterId: Int) =
    CharactersClient.instance
      .getComicsByCharacterId(characterId)
      .data
      .results
      .map { it.thumbnail.toUrlString() }
}

private fun RemoteCharacter.toDomainModel(): Character =
  Character(
    id = id,
    name = name,
    description = description,
    imageUrl = thumbnail.toUrlString(),
    comics = comics.items.map { it.name }
  )

private fun Thumbnail.toUrlString(): String = "${path.replace("http:", "https:")}.${extension}"