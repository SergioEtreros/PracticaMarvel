package com.senkou.practicamarvel.domain.character.usecases

import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetCharacterDetailsUseCaseTest {

   @Test
   fun `invoke calls repository`() {
      val charactersFlow = flowOf(sampleCharacter(2))
      val useCase = GetCharacterDetailsUseCase(mock {
         on { fetchCharacterById(2) } doReturn charactersFlow
      })

      val result = useCase(2)

      assertEquals(charactersFlow, result)
   }
}