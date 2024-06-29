package com.senkou.practicamarvel.data

import com.senkou.practicamarvel.data.datasource.CharactersLocalDataSource
import com.senkou.practicamarvel.data.datasource.CharactersRemoteDataSource
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import com.senkou.practicamarvel.domain.model.Character as DomainCharacter
import com.senkou.practicamarvel.framework.database.room.model.entities.Character as RoomCharacter

class CharactersRepository(
  private val charactersLocalDatasource: CharactersLocalDataSource,
  private val charactersRemoteDatasource: CharactersRemoteDataSource
) {

  val characters = charactersLocalDatasource.characters.onEach { localCharacters ->

    if (localCharacters.isEmpty()) {
      val remoteCharacters = charactersRemoteDatasource.getCharacters()
      charactersLocalDatasource.saveAllCharacters(remoteCharacters)
    }
  }.map { localCharacters -> localCharacters.map { it.toDomainCharacter() } }

  fun fetchCharacterById(id: Int) =
    charactersLocalDatasource.getCharacter(id).onEach { localCharacter ->
      if (localCharacter == null) {
        val remoteCharacter = charactersRemoteDatasource
          .getCharacterById(id)

        charactersLocalDatasource.saveCharacter(remoteCharacter)
      }
    }.map { checkNotNull(it).toDomainCharacter() }.filterNotNull()

  fun getComicsByCharacterId(characterId: Int) =
    charactersLocalDatasource.getComicsByCharacterId(characterId).onEach { localComics ->
      if (localComics.isEmpty()) {
        val remoteComics = charactersRemoteDatasource
          .getComicsByCharacterId(characterId)

        charactersLocalDatasource.saveComics(remoteComics)
      }
    }.map { comics -> comics.map { it.imgUrl } }

  suspend fun favoriteToggle(character: DomainCharacter) {
    charactersLocalDatasource.saveCharacter(
      character.copy(favorite = !character.favorite)
    )
  }
}

private fun RoomCharacter.toDomainCharacter(): DomainCharacter =
  DomainCharacter(
    id = id,
    name = name,
    description = description,
    imageUrl = imageUrl,
    favorite = favorite
  )
