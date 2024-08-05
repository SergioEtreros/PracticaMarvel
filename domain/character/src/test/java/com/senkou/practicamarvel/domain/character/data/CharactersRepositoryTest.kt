package com.senkou.practicamarvel.domain.character.data

import com.senkou.practicamarvel.domain.character.entities.Character
import com.senkou.practicamarvel.domain.character.usecases.sampleCharacter
import com.senkou.practicamarvel.domain.character.usecases.sampleCharacters
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.argThat
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class CharactersRepositoryTest {
   @Mock
   lateinit var localDataSource: CharactersLocalDataSource

   @Mock
   lateinit var remoteDataSource: CharactersRemoteDataSource

   private lateinit var repository: CharactersRepository

   @Before
   fun setUp() {
      repository = CharactersRepository(localDataSource, remoteDataSource)
   }

   @Test
   fun `characters are taken from local data source if available`(): Unit = runBlocking {
      val localCharacters = sampleCharacters(1, 4)
      whenever(localDataSource.characters).thenReturn(flowOf(localCharacters))

      val result = repository.characters

      assertEquals(localCharacters, result.first())
   }

   @Test
   fun `Characters are saved to local data source when it's empty`(): Unit = runBlocking {
      val localCharacters = emptyList<Character>()
      val remoteCharacters = sampleCharacters(1, 3, 5)
      whenever(localDataSource.characters).thenReturn(flowOf(localCharacters))
      whenever(remoteDataSource.getCharacters()).thenReturn(remoteCharacters)

      repository.characters.first()

      verify(localDataSource).saveAllCharacters(remoteCharacters)
   }

   @Test
   fun `Toggling favourite updates local data source`(): Unit = runBlocking {
      val character = sampleCharacter(2)
      repository.favoriteToggle(character)
      verify(localDataSource).saveCharacter(argThat { id == 2 })
   }

   @Test
   fun `Switching favorite marks as favorite an unfavorite Character`(): Unit = runBlocking {
      val character = sampleCharacter(2)
      repository.favoriteToggle(character)

      verify(localDataSource).saveCharacter(argThat { favorite })
   }

   @Test
   fun `Switching favorite marks as unfavorite a favorite Character`(): Unit = runBlocking {
      val character = sampleCharacter(2).copy(favorite = true)
      repository.favoriteToggle(character)

      verify(localDataSource).saveCharacter(argThat { !favorite })
   }


}