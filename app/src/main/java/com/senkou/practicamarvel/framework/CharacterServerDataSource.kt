package com.senkou.practicamarvel.framework

import com.senkou.practicamarvel.data.datasource.CharactersRemoteDataSource
import com.senkou.practicamarvel.domain.model.Character
import com.senkou.practicamarvel.domain.model.Comic
import com.senkou.practicamarvel.framework.remote.marvel.CharactersService
import com.senkou.practicamarvel.framework.remote.marvel.model.RemoteCharacter
import com.senkou.practicamarvel.framework.remote.marvel.model.RemoteComic
import com.senkou.practicamarvel.framework.remote.marvel.model.Thumbnail

class CharacterServerDataSource(
  private val charactersService: CharactersService
) : CharactersRemoteDataSource {
  override suspend fun getCharacters() = charactersService.getCharacters().data.results.filter {
    !it.thumbnail.path.contains("image_not_available") &&
        it.thumbnail.extension != "gif"
  }.map { it.toDomainCharacter() }

  override suspend fun getCharacterById(id: Int) =
    charactersService.getCharacterById(id).data.results.first().toDomainCharacter()

  override suspend fun getComicsByCharacterId(id: Int) =
    charactersService.getComicsByCharacterId(id).data.results.map { it.toDomainComic(id) }
}

private fun RemoteCharacter.toDomainCharacter(): Character =
  Character(
    id = id,
    name = name,
    description = description,
    imageUrl = thumbnail.toUrlString(),
    favorite = false
  )

private fun RemoteComic.toDomainComic(characterId: Int): Comic =
  Comic(
    id = id,
    characterId = characterId,
    imgUrl = thumbnail.toUrlString()
  )

private fun Thumbnail.toUrlString(): String = "${path.replace("http:", "https:")}.${extension}"