package com.senkou.practicamarvel.domain.character.data

import com.senkou.practicamarvel.domain.character.entities.Character
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class CharactersRepository @Inject constructor(
   private val charactersLocalDatasource: CharactersLocalDataSource,
   private val charactersRemoteDatasource: CharactersRemoteDataSource
) {

   val characters
      get() = charactersLocalDatasource.characters.onEach { localCharacters ->

         if (localCharacters.isEmpty()) {
            val remoteCharacters = charactersRemoteDatasource.getCharacters()
            charactersLocalDatasource.saveAllCharacters(remoteCharacters)
         }
      }

   fun fetchCharacterById(id: Int) =
      charactersLocalDatasource.getCharacter(id).onEach { localCharacter ->
         if (localCharacter == null) {
            val remoteCharacter = charactersRemoteDatasource
               .getCharacterById(id)

            charactersLocalDatasource.saveCharacter(remoteCharacter)
         }
      }
         .filterNotNull()

   suspend fun getComicsByCharacterId(characterId: Int): List<String> {
      val localComics = charactersLocalDatasource.getComicsByCharacterId(characterId)
      if (localComics.isEmpty()) {
         val remoteComics = charactersRemoteDatasource
            .getComicsByCharacterId(characterId)

         charactersLocalDatasource.saveComics(remoteComics)
      }

      return charactersLocalDatasource.getComicsByCharacterId(characterId)
   }

   suspend fun favoriteToggle(character: Character) {
      charactersLocalDatasource.saveCharacter(
         character.copy(favorite = !character.favorite)
      )
   }
}
