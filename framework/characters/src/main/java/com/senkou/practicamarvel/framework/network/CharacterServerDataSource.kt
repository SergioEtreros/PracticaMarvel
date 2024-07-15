package com.senkou.practicamarvel.framework.network

import com.senkou.practicamarvel.framework.network.model.RemoteCharacter
import com.senkou.practicamarvel.framework.network.model.RemoteComic
import com.senkou.practicamarvel.framework.network.model.Thumbnail

class CharacterServerDataSource(
  private val charactersService: CharactersService
) : com.senkou.practicamarvel.domain.character.data.CharactersRemoteDataSource {
  override suspend fun getCharacters() = charactersService.getCharacters().data.results.filter {
    !it.thumbnail.path.contains("image_not_available") &&
        it.thumbnail.extension != "gif"
  }.map { it.toDomainCharacter() }

  override suspend fun getCharacterById(id: Int) =
    charactersService.getCharacterById(id).data.results.first().toDomainCharacter()

  override suspend fun getComicsByCharacterId(id: Int) =
    charactersService.getComicsByCharacterId(id).data.results.map { it.toDomainComic(id) }
}

private fun RemoteCharacter.toDomainCharacter(): com.senkou.practicamarvel.domain.character.entities.Character =
  com.senkou.practicamarvel.domain.character.entities.Character(
    id = id,
    name = name,
    description = description,
    imageUrl = thumbnail.toUrlString(),
    favorite = false
  )

private fun RemoteComic.toDomainComic(characterId: Int): com.senkou.practicamarvel.domain.character.entities.Comic =
  com.senkou.practicamarvel.domain.character.entities.Comic(
    id = id,
    characterId = characterId,
    imgUrl = thumbnail.toUrlString()
  )

private fun Thumbnail.toUrlString(): String = "${path.replace("http:", "https:")}.${extension}"