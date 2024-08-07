package com.senkou.practicamarvel.domain.character.usecases

import com.senkou.practicamarvel.domain.character.data.CharactersRepository
import com.senkou.practicamarvel.test.unit.sampleCharacter
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class FavoriteToggleUseCaseTest {

   @Test
   fun `Invoke calls repository`(): Unit = runBlocking {
      val character = sampleCharacter(2)
      val repository = mock<CharactersRepository>()
      val useCase = FavoriteToggleUseCase(repository)

      useCase(character)

      verify(repository).favoriteToggle(character)
   }
}