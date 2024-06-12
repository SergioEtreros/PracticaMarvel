package com.senkou.practicamarvel.data

import com.senkou.practicamarvel.data.local.room.CharactersLocalDataSource
import com.senkou.practicamarvel.data.local.room.model.entities.Comics
import com.senkou.practicamarvel.data.network.marvel.CharactersRemoteDataSource
import com.senkou.practicamarvel.data.network.marvel.model.RemoteCharacter
import com.senkou.practicamarvel.data.network.marvel.model.RemoteComic
import com.senkou.practicamarvel.data.network.marvel.model.Thumbnail
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import com.senkou.practicamarvel.data.local.room.model.entities.Character as RoomCharacter
import com.senkou.practicamarvel.domain.model.Character as DomainCharacter

class CharactersRepository(
  private val charactersRemoteDatasource: CharactersRemoteDataSource,
  private val charactersLocalDatasource: CharactersLocalDataSource
) {

  val characters = charactersLocalDatasource.characters.onEach { localCharacters ->

    if (localCharacters.isEmpty()) {
      val remoteCharacters =
        charactersRemoteDatasource.instance
          .getCharacters()
          .data
          .results
          .filter {
            !it.thumbnail.path.contains("image_not_available") &&
                it.thumbnail.extension != "gif"
          }

      charactersLocalDatasource.saveAllCharacters(remoteCharacters.map { it.toRoomModel() })
    }
  }.map { localCharacters -> localCharacters.map { it.toDomainCharacter() } }

  fun fetchCharacterById(id: Int) =
    charactersLocalDatasource.getCharacter(id).onEach { localCharacter ->
      if (localCharacter == null) {
        val remoteCharacter = charactersRemoteDatasource.instance
          .getCharacterById(id)
          .data
          .results
          .first()

        charactersLocalDatasource.saveCharacter(remoteCharacter.toRoomModel())
      }
    }.map { checkNotNull(it).toDomainCharacter() }.filterNotNull()

  fun getComicsByCharacterId(characterId: Int) =
    charactersLocalDatasource.getComicsByCharacterId(characterId).onEach { localComics ->
      if (localComics.isEmpty()) {
        val remoteComics = charactersRemoteDatasource.instance
          .getComicsByCharacterId(characterId)
          .data
          .results

        charactersLocalDatasource.saveComics(remoteComics.map { it.toRoomModel(characterId) })
      }
    }.map { comics -> comics.map { it.imgUrl } }

  suspend fun favoriteToggle(character: DomainCharacter) {
    charactersLocalDatasource.saveCharacter(
      character.toRoomModel().copy(favorite = !character.favorite)
    )
  }
}

private fun DomainCharacter.toRoomModel(): RoomCharacter =
  RoomCharacter(
    id = id,
    name = name,
    description = description,
    imageUrl = imageUrl,
    favorite = favorite
  )

private fun RoomCharacter.toDomainCharacter(): DomainCharacter =
  DomainCharacter(
    id = id,
    name = name,
    description = description,
    imageUrl = imageUrl,
    favorite = favorite
  )

private fun RemoteCharacter.toRoomModel(): RoomCharacter =
  RoomCharacter(
    id = id,
    name = name,
    description = description,
    imageUrl = thumbnail.toUrlString(),
    favorite = false
  )

private fun RemoteComic.toRoomModel(characterId: Int): Comics =
  Comics(
    id = id,
    characterId = characterId,
    imgUrl = thumbnail.toUrlString()
  )

private fun Thumbnail.toUrlString(): String = "${path.replace("http:", "https:")}.${extension}"