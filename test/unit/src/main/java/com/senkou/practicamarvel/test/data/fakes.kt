package com.senkou.practicamarvel.test.data

import com.senkou.practicamarvel.domain.character.data.CharactersLocalDataSource
import com.senkou.practicamarvel.domain.character.data.CharactersRemoteDataSource
import com.senkou.practicamarvel.domain.character.data.CharactersRepository
import com.senkou.practicamarvel.domain.character.entities.Character
import com.senkou.practicamarvel.domain.character.entities.Comic
import com.senkou.practicamarvel.test.unit.sampleComicsObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

fun buildCharacterRepositoryWith(
   localDataCharacter: List<Character> = emptyList(),
   localDataComics: List<Comic> = emptyList(),
   remoteDataCharacter: List<Character> = emptyList(),
): CharactersRepository {
   val localDataSource = FakeLocalDataSource().apply {
      inMemoryCharacters.value = localDataCharacter
      inMemoryComics = localDataComics
   }

   val remoteDataSource = FakeRemoteDataSource(remoteDataCharacter)

   return CharactersRepository(localDataSource, remoteDataSource)
}

class FakeRemoteDataSource(private val characters: List<Character>) : CharactersRemoteDataSource {

   override suspend fun getCharacters(): List<Character> = characters

   override suspend fun getCharacterById(id: Int): Character =
      characters.first { character -> character.id == id }

   override suspend fun getComicsByCharacterId(id: Int): List<Comic> =
      sampleComicsObject(id, 2, 4, 6)

}

class FakeLocalDataSource : CharactersLocalDataSource {

   val inMemoryCharacters = MutableStateFlow<List<Character>>(emptyList())
   var inMemoryComics = listOf<Comic>()

   override val characters: Flow<List<Character>> = inMemoryCharacters

   override fun getCharacter(id: Int): Flow<Character?> =
      inMemoryCharacters.map { it.firstOrNull { character -> character.id == id } }

   override suspend fun saveCharacter(character: Character) {
      inMemoryCharacters.value =
         inMemoryCharacters.value.filter { it.id != character.id } + character
   }

   override suspend fun saveAllCharacters(characters: List<Character>) {
      inMemoryCharacters.value = characters
   }

   override suspend fun getComicsByCharacterId(id: Int): List<String> =
      inMemoryComics.filter { comic -> comic.characterId == id }.map { item -> item.imgUrl }

   override suspend fun saveComics(comics: List<Comic>) {
      inMemoryComics = comics
   }

   override suspend fun deleteAll() {
      inMemoryCharacters.value = emptyList()
      inMemoryComics = emptyList()
   }
}
