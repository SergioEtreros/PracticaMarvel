package com.senkou.practicamarvel.framework.network

import com.senkou.practicamarvel.domain.character.data.CharactersRemoteDataSource
import com.senkou.practicamarvel.domain.character.entities.Character
import com.senkou.practicamarvel.domain.character.entities.Comic
import com.senkou.practicamarvel.framework.network.model.RemoteCharacter
import com.senkou.practicamarvel.framework.network.model.RemoteComic
import com.senkou.practicamarvel.framework.network.model.Thumbnail
import javax.inject.Inject

internal class CharacterServerDataSource @Inject constructor(
  private val charactersService: CharactersService
) : CharactersRemoteDataSource {
  override suspend fun getCharacters(): List<Character> {
    val response = charactersService.getCharacters()
    return response.data.results.filter {
      !it.thumbnail.path.contains("image_not_available") &&
            it.thumbnail.extension != "gif"
    }.map { it.toDomainCharacter() }
  }

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